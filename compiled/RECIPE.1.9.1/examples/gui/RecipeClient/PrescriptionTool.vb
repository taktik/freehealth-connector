Imports be.business.connector.recipe.prescriber
Imports be.business.connector.core.utils
Imports be.business.connector.common
Imports System.Windows.Forms
Imports be.business.connector.session
Imports be.recipe.services.prescriber
Imports System.Text.RegularExpressions

Public Class PrescriptionTool

    Dim moduleInstance
    Dim init = False
    Dim systemInitialized = False
    Dim listRidsHistoryPageNumber As Integer = 0
    Dim listOpenRidsPageNumber As Integer = 0


    Private Sub InitSystem()
        If systemInitialized = False Then

            Me.SelectedExpirationDateLabel.Text = getDefaultExpirationDate()
            Me.ExpirationDate.SetDate(DateTime.Now().AddMonths(3))
            Me.ExpirationDate.MinDate = DateTime.Now()
            Me.ExpirationDate.MaxDate = DateTime.Now().AddMonths(12).AddDays(-1)
            Me.ListRidsHistoryPageLabel.Text = "Page: " & listRidsHistoryPageNumber
            Me.ListOpenRidsPageLabel.Text = "Page: " & listOpenRidsPageNumber
            Me.PrescriptionContent.Text = Me.PrescriptionContent.Text.Replace("EXPIRATION_DATE_PLACEHOLDER", getDefaultExpirationDate())
            If InStr(Command$, "-mock=true") Then
                ApplicationConfig.getInstance().initialize()
                systemInitialized = True
            Else
                Dim config As String = Nothing
                Dim configValidation As String = Nothing
                Dim foundConfig As Boolean = False
                Dim foundValidationConfig As Boolean = False
                For Each argument In Command$().Split(" ")
                    If InStr(argument, "-config=") Then
                        foundConfig = True
                        config = argument.ToString.Substring(8)
                        Debug.Print("Config file used  " & config)
                    End If
                    If InStr(argument, "-configValidation=") Then
                        foundValidationConfig = True
                        configValidation = argument.ToString.Substring(18)
                        Debug.Print("Config file used  " & configValidation)
                    End If
                    If foundConfig And foundValidationConfig Then
                        Exit For
                    End If
                Next
                If Not String.IsNullOrEmpty(config) And Not String.IsNullOrEmpty(configValidation) Then
                    ApplicationConfig.getInstance().initialize(config, configValidation)
                    systemInitialized = True
                    Exit Sub
                End If
            End If
        End If
    End Sub

    Function GetModule() As PrescriberIntegrationModuleV4
        InitSystem()
        GetModule = New PrescriberIntegrationModuleV4Impl
    End Function

    Function createNewSession(ByVal sessionType As SessionType, ByVal inss As String, ByVal password As String) As String
        Dim sessionItem As be.ehealth.technicalconnector.session.SessionItem
        Dim resultSession As String
        InitSystem()
        If InStr(Command$, "-mock=true") Then
            resultSession = "Create session not supported for mock"
        Else
            sessionItem = SessionUtil.createSession(sessionType, PropertyHandler.getInstance().getPropertiesCopy, inss, password)
            resultSession = SAML10Converter.toXMLString(sessionItem.getSAMLToken.getAssertion)
        End If
        createNewSession = resultSession
    End Function


    Private Sub showError(ByVal p1 As ErrObject)

        If TypeOf p1.GetException Is be.business.connector.core.exceptions.IntegrationModuleValidationException Then
            Dim validationErrorsString As String = ""
            Dim err As be.business.connector.core.exceptions.IntegrationModuleValidationException = TryCast(p1.GetException, be.business.connector.core.exceptions.IntegrationModuleValidationException)
            If err.getValidationErrors IsNot Nothing Then
                For i = 0 To err.getValidationErrors.size - 1
                    Dim item = err.getValidationErrors.get(i)
                    validationErrorsString &= item & vbCrLf
                Next
            End If
            MessageBox.Show(validationErrorsString, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error)
        ElseIf TypeOf p1.GetException Is be.business.connector.core.exceptions.IntegrationModuleException Then
            Dim errorsString As String = ""
            Dim err As be.business.connector.core.exceptions.IntegrationModuleException = TryCast(p1.GetException, be.business.connector.core.exceptions.IntegrationModuleException)
            ''Ignore default error message
            ''errorsString &= err.getMessage & vbCrLf
            If err.getMessages IsNot Nothing Then
                For i = 0 To err.getMessages.size - 1
                    Dim localString As be.recipe.services.core.LocalisedString = err.getMessages.get(i)
                    If localString.getLang().toString = "EN" Then
                        errorsString &= localString.getValue() & vbCrLf
                    End If
                Next
            End If
            If err.getMessage IsNot Nothing And err.getMessages IsNot Nothing And err.getMessages.size = 0 Then
                errorsString &= "Error: " & err.getMessage & vbCrLf
            End If
            If err.getMessageCode IsNot Nothing Then
                errorsString &= "ErrorCode: " & err.getMessageCode & vbCrLf
            End If
            If err.getPrescriptionStatus IsNot Nothing Then
                errorsString &= "PrescriptionStatus: " & err.getPrescriptionStatus.toString & vbCrLf
            End If
            If err.getStatusUpdater IsNot Nothing Then
                errorsString &= "StatusUpdater: " & err.getStatusUpdater & vbCrLf
            End If
            MessageBox.Show(errorsString, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error)
        Else
            If Not String.IsNullOrEmpty(p1.Description) Then
                MessageBox.Show(p1.Description, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error)
            End If
        End If
    End Sub


    Private Sub CreatePrescription_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles CreatePrescription.Click
        On Error GoTo ERR

        CreatePrescription.Enabled = False

        Dim prescription As Byte()
        Dim encoding As New System.Text.UTF8Encoding()
        prescription = encoding.GetBytes(PrescriptionContent.Text)

        Dim dateTime As String = SelectedExpirationDateLabel.Text
        Dim dt As DateTime = Convert.ToDateTime(dateTime)
        Dim format As String = "yyyy-MM-dd"
        Dim expirationDate As String = dt.ToString(format)

        Dim rid As String
        If BulkCheckBox.Checked Then
            Dim dtos As java.util.List = New java.util.ArrayList
            For i = 1 To Integer.Parse(BulkAmountTextBox.Text)
                Dim dto As be.business.connector.recipe.prescriber.dto.CreatePrescriptionDTO = New be.business.connector.recipe.prescriber.dto.CreatePrescriptionDTO(i, Feedback.Checked,
                                                       PatientID.Text.ToString,
                                                       prescription, PrescriptionType.Text, VisionTextBox.Text)
                dtos.add(dto)
            Next

            Dim result = GetModule().createPrescriptions(dtos)

            ''Dim result As java.util.ArrayList = New java.util.ArrayList
            ''For i = 1 To Integer.Parse(BulkAmountTextBox.Text)
            ''Dim item As be.business.connector.recipe.prescriber.dto.CreatePrescriptionDTO = New be.business.connector.recipe.prescriber.dto.CreatePrescriptionDTO(1, True, "80011904703", Nothing, Nothing, Nothing)
            '' item.setRid("BEP012345678")
            '' result.add(item)
            ''Next
            Dim resultPopup As ResultPopup = New ResultPopup
            resultPopup.TopMost = True
            resultPopup.initResults(result)
            resultPopup.ShowDialog()

        Else
            rid = GetModule().createPrescription(Feedback.Checked,
                                                       PatientID.Text.ToString,
                                                       prescription, PrescriptionType.Text, VisionTextBox.Text, expirationDate)
            InputBox("Prescription " & rid & " successfully created!", "CreatePrescription()", rid)

            RIDTextBox.Text = rid
        End If
        CreatePrescription.Enabled = True
        Exit Sub
ERR:
        showError(Err)
        CreatePrescription.Enabled = True
    End Sub

    Private Sub GetPrescription_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles GetPrescription.Click
        On Error GoTo ERR
        GetPrescription.Enabled = False

        Dim p As be.recipe.services.prescriber.GetPrescriptionForPrescriberResult
        p = GetModule().getPrescription(RIDTextBox.Text)

        GetPrescriptionContent.Text = IOUtils.strConvert(p.getPrescription)
        GetPrescriptionPatientID.Text = p.getPatientId
        GetPrescriptionDate.Text = p.getCreationDate.getTime.toString()

        RequestFeedback.Enabled = False
        RequestFeedback.Checked = p.isFeedbackAllowed
        RequestFeedback.Enabled = True
        GetPrescription.Enabled = True
        Exit Sub
ERR:
        showError(Err)
        GetPrescription.Enabled = True

    End Sub

    Private Sub RevokePrescription_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles RevokePrescription.Click
        On Error GoTo ERR
        RevokePrescription.Enabled = False

        GetModule().revokePrescription(RIDTextBox.Text, InputBox("Revoke Reason:"))

        MessageBox.Show("Prescription " & RIDTextBox.Text & " successfully revoked!")

        RevokePrescription.Enabled = True
        Exit Sub
ERR:
        showError(Err)
        RevokePrescription.Enabled = True
    End Sub

    Private Sub RequestFeedback_CheckedChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles RequestFeedback.CheckedChanged
        On Error GoTo ERR

        ' Only update feedback flag if request comes from the user (check GetPrescription)
        If RequestFeedback.Enabled = True Then
            RequestFeedback.Enabled = False
            GetModule().updateFeedbackFlag(RIDTextBox.Text, RequestFeedback.Checked)
            MessageBox.Show("Flag updated!")
            RequestFeedback.Enabled = True
        End If

        Exit Sub
ERR:
        showError(Err)
        RequestFeedback.Enabled = True
    End Sub

    Private Sub ListFeedbacks_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ListFeedbacks.Click
        On Error GoTo ERR

        ListFeedbacks.Enabled = False
        Dim i As Integer
        Dim ListFeedbacksRes As java.util.List
        Dim item As be.recipe.services.prescriber.ListFeedbackItem

        ListFeedbacksRes = GetModule().listFeedback(Not (ListFeedbacksUnread.Checked))
        ListFeedbacksResult.Text = ""
        For i = 0 To ListFeedbacksRes.size - 1
            item = ListFeedbacksRes.get(i)
            ListFeedbacksResult.Text &= "Sent by : " + item.getSentBy.ToString & vbCrLf
            ListFeedbacksResult.Text &= "Sent date : " + item.getSentDate.getTime.toString & vbCrLf
            ListFeedbacksResult.Text &= "RID : " + item.getRid & vbCrLf
            ListFeedbacksResult.Text &= "-------------------------------------------------" & vbCrLf
            ListFeedbacksResult.Text &= IOUtils.strConvert(item.getContent) & vbCrLf
            ListFeedbacksResult.Text &= "-------------------------------------------------" & vbCrLf
        Next

        ListFeedbacks.Enabled = True
        Exit Sub
ERR:
        showError(Err)
        ListFeedbacks.Enabled = True

    End Sub

    Private Sub ListOpenPrescriptionButton_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ListOpenPrescriptionButton.Click
        On Error GoTo ERR
        DisableListOpenRidsButtons()
        ListOpenRids()

ERR:
        showError(Err)
        EnableListOpenRidsButtons()
    End Sub

    Private Sub ListOpenRids()

        ListOpenRidsPageLabel.Text = "Page: " & listOpenRidsPageNumber

        Dim param As ListOpenRidsParam = New ListOpenRidsParam

        param.setPatientId(ListPrescrPatientId.Text)
        Dim Page As be.recipe.services.core.Page = New be.recipe.services.core.Page
        Dim bi As java.math.BigInteger
        bi = java.math.BigInteger.valueOf(listOpenRidsPageNumber)
        Page.setPageNumber(bi)
        param.setPage(Page)

        Dim ListOpenRidsResult As be.recipe.services.prescriber.ListOpenRidsResult = GetModule().getData(param)

        ListOpenPrescriptionResult.Text = ""
        If ListOpenRidsResult.getPrescriptions() IsNot Nothing Then
            For i = 0 To ListOpenRidsResult.getPrescriptions.size - 1
                ListOpenPrescriptionResult.Text &= ListOpenRidsResult.getPrescriptions.get(i) & vbCrLf
            Next
        Else
            ListOpenPrescriptionResult.Clear()
        End If

        If ListOpenRidsResult.getHasMoreResults.booleanValue Then
            ListOpenPrescriptionsPreviousButton.Enabled = True
        Else
            ListOpenPrescriptionsPreviousButton.Enabled = False
        End If
        EnableListOpenRidsButtons()
        Exit Sub

    End Sub

    Private Sub ListOpenPrescriptionsPreviousButton_Click(sender As Object, e As EventArgs) Handles ListOpenPrescriptionsPreviousButton.Click
        On Error GoTo ERR
        listOpenRidsPageNumber = listOpenRidsPageNumber + 1
        ListOpenRids()
        Exit Sub
ERR:
        showError(Err)
        EnableListOpenRidsButtons()
    End Sub

    Private Sub ListOpenPrescriptionsNextButton_Click(sender As Object, e As EventArgs) Handles ListOpenPrescriptionsNextButton.Click
        On Error GoTo ERR
        listOpenRidsPageNumber = listOpenRidsPageNumber - 1
        ListOpenRids()
        Exit Sub
ERR:
        showError(Err)
        EnableListOpenRidsButtons()
    End Sub

    Private Sub EnableListOpenRidsButtons()
        ListOpenPrescriptionsNextButton.Enabled = True
        'ListOpenPrescriptionsPreviousButton.Enabled = True
        ListOpenPrescriptionButton.Enabled = True
        If listOpenRidsPageNumber = 0 Then
            ListOpenPrescriptionsNextButton.Enabled = False
        Else
            ListOpenPrescriptionsNextButton.Enabled = True
        End If
    End Sub

    Private Sub DisableListOpenRidsButtons()
        ListOpenPrescriptionButton.Enabled = False
        ListOpenPrescriptionsPreviousButton.Enabled = False
        ListOpenPrescriptionsNextButton.Enabled = False
    End Sub

    Private Sub ListRidsHistoryButton_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ListRidsHistoryButton.Click
        On Error GoTo Err
        DisabledListRidsHistoryButtons()

        ListRidsHistory()

        EnabledListRidsHistoryButtons()
        Exit Sub
Err:
        showError(Err)
        EnabledListRidsHistoryButtons()
    End Sub

    Private Sub ListRidsHistory()
        'On Error GoTo ERR
        DisabledListRidsHistoryButtons()
        ListRidsHistoryPageLabel.Text = "Page: " & listRidsHistoryPageNumber
        Dim Page As be.recipe.services.core.Page = New be.recipe.services.core.Page
        Dim bi As java.math.BigInteger
        bi = java.math.BigInteger.valueOf(listRidsHistoryPageNumber)
        Page.setPageNumber(bi)

        Dim param As be.recipe.services.prescriber.ListRidsHistoryParam = New be.recipe.services.prescriber.ListRidsHistoryParam
        param.setPage(Page)
        param.setPatientId(ListRidsHistoryTextBox.Text)
        If ListRidsHistoryCheckBox.Checked Then
            param.setActiveResults(java.lang.Boolean.TRUE)
        Else
            param.setActiveResults(java.lang.Boolean.FALSE)
        End If

        Dim ListRidsHistoryResult As be.recipe.services.prescriber.ListRidsHistoryResult = GetModule().getData(param)

        ListRidsHistoryRichTextBox.Text = ""
        If ListRidsHistoryResult.getItems() IsNot Nothing Then
            For i = 0 To ListRidsHistoryResult.getItems().size - 1
                Dim item As be.recipe.services.prescriber.ListRidsHistoryResultItem = ListRidsHistoryResult.getItems().get(i)
                ListRidsHistoryRichTextBox.Text &= item.getRid & ": " & item.getPrescriptionStatus.name & vbCrLf
            Next
        Else
            ListRidsHistoryRichTextBox.Clear()
        End If

        If ListRidsHistoryResult.getHasMoreResults.booleanValue Then
            ListRidsHistoryPreviousPageButton.Enabled = True
        Else
            ListRidsHistoryPreviousPageButton.Enabled = False
        End If

        EnabledListRidsHistoryButtons()
        Exit Sub
        'ERR:
        '       showError(Err)
        '      EnabledListRidsHistoryButtons()
    End Sub

    Private Sub SendNotification_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles SendNotification.Click
        On Error GoTo ERR
        SendNotification.Enabled = False

        Dim notification As Byte()
        Dim encoding As New System.Text.UTF8Encoding()

        Dim bNotification As String = "<?xml version='1.0' encoding='ISO-8859-1'?><p:notification xmlns:p='http://services.recipe.be' xmlns:xsi = 'http://www.w3.org/2001/XMLSchema-instance' xsi:schemaLocation = 'http://services.recipe.be notification.xsd' > "
        Dim message As String = "<text>" & txtNotification.Text & "</text>"
        Dim prescription As String = txtPrescriptionNotification.Text
        Dim eNotification As String = "</p:notification>"

        Dim notif As String = bNotification & message & prescription & eNotification

        notification = encoding.GetBytes(notif)


        GetModule().sendNotification(notification, NotificationPatientID.Text, NotificationExecutorID.Text)

        MessageBox.Show("Notification successfully sent")

        SendNotification.Enabled = True
        Exit Sub
ERR:
        showError(Err)
        SendNotification.Enabled = True
    End Sub

    Private Sub LoginButton_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles LoginButton.Click
        On Error GoTo ERR
        LoginButton.Enabled = False
        LoginSAMLResponse.Text = createNewSession(SessionType.EID_SESSION, Nothing, Nothing)
        LoginButton.Enabled = True
        Exit Sub
ERR:
        showError(Err)
        LoginButton.Enabled = True
    End Sub


    Private Sub btnCreateFallbackSession_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnCreateFallbackSession.Click
        On Error GoTo ERR

        btnCreateFallbackSession.Enabled = False

        If PasswordPrompt.ShowDialog() = DialogResult.OK Then
            LoginSAMLResponse.Text = createNewSession(SessionType.FALLBACK_SESSION, PasswordPrompt.niss.Text, PasswordPrompt.password.Text)
        End If

        btnCreateFallbackSession.Enabled = True
        Exit Sub
ERR:
        showError(Err)
        btnCreateFallbackSession.Enabled = True
    End Sub

    Private Sub PrescriptionTool_Activated(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Activated
        On Error GoTo ERR

        GetModule()

        Exit Sub
ERR:
        showError(Err)
    End Sub


    Private Sub GetPrescriptionStatus_Click(sender As Object, e As EventArgs) Handles GetPrescriptionStatus.Click
        On Error GoTo ERR
        GetPrescriptionStatus.Enabled = False

        'Dim PrescriptionStatus As be.recipe.services.core.PrescriptionStatus
        Dim param As be.recipe.services.prescriber.GetPrescriptionStatusParam = New be.recipe.services.prescriber.GetPrescriptionStatusParam
        param.setRid(RIDTextBox.Text)

        Dim result As be.recipe.services.prescriber.GetPrescriptionStatusResult = GetModule().getData(param)

        MessageBox.Show("PrescriptionStatus of prescription: " & RIDTextBox.Text & " is: " & result.getPrescriptionStatus().name)

        GetPrescriptionStatus.Enabled = True
        Exit Sub
ERR:
        showError(Err)
        GetPrescriptionStatus.Enabled = True
    End Sub

    Private Sub PreviousPageButton_Click(sender As Object, e As EventArgs) Handles ListRidsHistoryPreviousPageButton.Click
        On Error GoTo ERR
        listRidsHistoryPageNumber = listRidsHistoryPageNumber + 1
        ListRidsHistory()
        Exit Sub
ERR:
        showError(Err)
        EnabledListRidsHistoryButtons()
    End Sub

    Private Sub NextPageButton_Click(sender As Object, e As EventArgs) Handles ListRidsHistoryNextPageButton.Click
        On Error GoTo ERR
        listRidsHistoryPageNumber = listRidsHistoryPageNumber - 1
        ListRidsHistory()
        Exit Sub
ERR:
        showError(Err)
        EnabledListRidsHistoryButtons()
    End Sub

    Private Sub ExpirationDate_DateChanged(sender As Object, e As DateRangeEventArgs) Handles ExpirationDate.DateSelected
        Me.SelectedExpirationDateLabel.Text = e.Start.ToString("yyyy-MM-dd")
        Me.SelectedExpirationDateLabel.Visible = True
        Me.ExpirationDate.Visible = False
        Me.PrescriptionContent.Text = Regex.Replace(Me.PrescriptionContent.Text, "<expirationdate>[\s\S]*?<\/expirationdate>", "<expirationdate>" & e.Start.ToString("yyyy-MM-dd") & "</expirationdate>")
    End Sub

    Sub SelectExpirationDateLabel_Click(sender As Object, e As EventArgs) Handles SelectedExpirationDateLabel.Click
        Me.SelectedExpirationDateLabel.Visible = False
        Me.ExpirationDate.Visible = True
    End Sub

    Private Sub Label20_Click_1(sender As Object, e As EventArgs) Handles VisionLabel.Click

    End Sub

    Private Sub DisabledListRidsHistoryButtons()
        ListRidsHistoryButton.Enabled = False
        ListRidsHistoryPreviousPageButton.Enabled = False
        ListRidsHistoryNextPageButton.Enabled = False
    End Sub

    Private Sub EnabledListRidsHistoryButtons()
        ListRidsHistoryButton.Enabled = True
        'ListRidsHistoryPreviousPageButton.Enabled = True
        If listRidsHistoryPageNumber = 0 Then
            ListRidsHistoryNextPageButton.Enabled = False
        Else
            ListRidsHistoryNextPageButton.Enabled = True
        End If
    End Sub

    Private Sub PutVisionButton_Click(sender As Object, e As EventArgs) Handles PutVisionButton.Click
        On Error GoTo ERR
        PutVisionButton.Enabled = False
        PutVisionRidTextBox.Enabled = False
        PutVisionTextBox.Enabled = False

        Dim param As be.recipe.services.prescriber.PutVisionParam = New be.recipe.services.prescriber.PutVisionParam
        param.setVision(PutVisionTextBox.Text)
        param.setRid(PutVisionRidTextBox.Text)

        Dim response As be.recipe.services.prescriber.PutVisionResult = GetModule().putData(param)
        If response.getStatus.getCode = 100 Then
            MessageBox.Show("Vision set for " & PutVisionRidTextBox.Text & " with value " & PutVisionTextBox.Text)
        Else
            Dim errorList As java.util.List = response.getStatus.getMessages()
            For i = 0 To errorList.size - 1
                Dim localString As be.recipe.services.core.LocalisedString = errorList.get(i)
                If localString.getLang().toString = "EN" Then
                    MessageBox.Show(localString.getValue())
                End If
            Next
        End If

        PutVisionButton.Enabled = True
        PutVisionRidTextBox.Enabled = True
        PutVisionTextBox.Enabled = True
        Exit Sub
ERR:
        showError(Err)
        PutVisionButton.Enabled = True
        PutVisionRidTextBox.Enabled = True
        PutVisionTextBox.Enabled = True
    End Sub

    'Private Sub BulkCheckBox_CheckedChanged(sender As Object, e As EventArgs) Handles BulkCheckBox.CheckedChanged
    'If BulkCheckBox.Checked Then
    'Me.ExpirationDate.Visible = False
    'Me.SelectedExpirationDateLabel.Visible = False
    'Me.ExpirtionDateLabel.Enabled = False
    'Else
    'Me.ExpirationDate.Visible = True
    'Me.SelectedExpirationDateLabel.Visible = True
    'Me.ExpirtionDateLabel.Enabled = True
    'End If
    'End Sub

    Public Function getDefaultExpirationDate() As String
        Return DateTime.Now().AddMonths(3).ToString("yyyy-MM-dd")
    End Function

End Class
