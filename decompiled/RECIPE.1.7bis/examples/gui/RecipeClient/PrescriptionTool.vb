Imports be.business.connector.recipe.prescriber
Imports be.business.connector.recipe.prescriber.mock
Imports be.business.connector.core.utils
Imports be.business.connector.common
Imports be.business.connector.recipe.prescriber.domain
Imports System.Windows.Forms
Imports be.business.connector.session

Public Class PrescriptionTool

    Dim moduleInstance
    Dim init = False
    Dim systemInitialized = False

    Private Sub InitSystem()
        If systemInitialized = False Then
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

    Function GetModule() As PrescriberIntegrationModule
        InitSystem()
        If InStr(Command$, "-mock=true") Then
            moduleInstance = New PrescriberIntegrationModuleMock
        Else
            moduleInstance = New PrescriberIntegrationModuleImpl
        End If
        GetModule = moduleInstance
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
        MessageBox.Show(p1.Description, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error)
    End Sub


    Private Sub CreatePrescription_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles CreatePrescription.Click
        On Error GoTo ERR

        CreatePrescription.Enabled = False

        Dim prescription As Byte()
        Dim encoding As New System.Text.UTF8Encoding()
        prescription = encoding.GetBytes(PrescriptionContent.Text)

        Dim rid As String

        rid = GetModule().createPrescription(Feedback.Checked,
                                                       Long.Parse(PatientID.Text.ToString),
                                                       prescription,
                                                       PrescriptionType.Text)

        InputBox("Prescription " & rid & " successfully created!", "CreatePrescription()", rid)

        RIDTextBox.Text = rid

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
        RequestFeedback.Checked = p.getFeedbackAllowed

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
        ListOpenPrescriptionButton.Enabled = False

        Dim ListOpenPrescriptions As java.util.List

        ListOpenPrescriptions = GetModule().listOpenPrescription(ListPrescrPatientId.Text)


        ListOpenPrescriptionResult.Text = ""
        For i = 0 To ListOpenPrescriptions.size - 1
            ListOpenPrescriptionResult.Text &= ListOpenPrescriptions.get(i) & vbCrLf
        Next

        ListOpenPrescriptionButton.Enabled = True
        Exit Sub
ERR:
        showError(Err)
        ListOpenPrescriptionButton.Enabled = True
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


        GetModule().sendNotification(notification, _
            Long.Parse(NotificationPatientID.Text), _
            Long.Parse(NotificationExecutorID.Text))

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

    Private Sub SetPrivKeyPwdButton_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles SetPrivKeyPwdButton.Click
        On Error GoTo ERR

        SetPrivKeyPwdButton.Enabled = False

        GetModule().setPersonalPassword(InputBox("Enter the passphrase of your personal E-Health Certificate", "", ""))

        SetPrivKeyPwdButton.Enabled = True
        Exit Sub
ERR:
        showError(Err)
        SetPrivKeyPwdButton.Enabled = True
    End Sub

    Private Sub PrescriptionTool_Activated(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Activated
        On Error GoTo ERR

        GetModule()

        Exit Sub
ERR:
        showError(Err)
    End Sub


    Private Sub btnSystemKeystoreSave_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnSystemKeystoreSave.Click
        On Error GoTo ERR
        btnSystemKeystoreSave.Enabled = False

        ApplicationConfig.getInstance().setSystemKeystoreProperties(txtSystemKeystorePass.Text, txtSystemKeystoreFile.Text, txtSystemkeystoreDir.Text, txtSystemkeystoreKboRiziv.Text)

        MessageBox.Show("System keystore information has been saved.")

        btnSystemKeystoreSave.Enabled = True
        Exit Sub
ERR:
        showError(Err)
        btnSystemKeystoreSave.Enabled = True
    End Sub

    Private Sub btnOldSystemkeystoreSave_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnOldSystemkeystoreSave.Click
        On Error GoTo ERR
        btnOldSystemkeystoreSave.Enabled = False

        ApplicationConfig.getInstance().setOldSystemKeystoreProperties(txtOldSystemKeystorePass.Text, txtOldSystemKeystoreFile.Text, txtOldSystemkeystoreDir.Text, txtOldSystemkeystoreKboRiziv.Text)

        MessageBox.Show("Previous System keystore information has been saved.")

        btnOldSystemkeystoreSave.Enabled = True
        Exit Sub
ERR:
        showError(Err)
        btnOldSystemkeystoreSave.Enabled = True
    End Sub
End Class
