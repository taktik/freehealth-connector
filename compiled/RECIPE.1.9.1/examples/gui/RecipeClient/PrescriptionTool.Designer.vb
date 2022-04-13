<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()>
Partial Class PrescriptionTool
    Inherits System.Windows.Forms.Form

    'Form remplace la méthode Dispose pour nettoyer la liste des composants.
    <System.Diagnostics.DebuggerNonUserCode()>
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        Try
            If disposing AndAlso components IsNot Nothing Then
                components.Dispose()
            End If
        Finally
            MyBase.Dispose(disposing)
        End Try
    End Sub

    'Requise par le Concepteur Windows Form
    Private components As System.ComponentModel.IContainer

    'REMARQUE : la procédure suivante est requise par le Concepteur Windows Form
    'Elle peut être modifiée à l'aide du Concepteur Windows Form.  
    'Ne la modifiez pas à l'aide de l'éditeur de code.
    <System.Diagnostics.DebuggerStepThrough()>
    Private Sub InitializeComponent()
        Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(PrescriptionTool))
        Me.TabControl1 = New System.Windows.Forms.TabControl()
        Me.TabPage6 = New System.Windows.Forms.TabPage()
        Me.btnCreateFallbackSession = New System.Windows.Forms.Button()
        Me.LoginButton = New System.Windows.Forms.Button()
        Me.Label13 = New System.Windows.Forms.Label()
        Me.LoginSAMLResponse = New System.Windows.Forms.RichTextBox()
        Me.TabPage1 = New System.Windows.Forms.TabPage()
        Me.BulkAmountTextBox = New System.Windows.Forms.TextBox()
        Me.BulkAmountLabel = New System.Windows.Forms.Label()
        Me.BulkLabel = New System.Windows.Forms.Label()
        Me.BulkCheckBox = New System.Windows.Forms.CheckBox()
        Me.ExpirationDate = New System.Windows.Forms.MonthCalendar()
        Me.SelectedExpirationDateLabel = New System.Windows.Forms.Label()
        Me.VisionTextBox = New System.Windows.Forms.TextBox()
        Me.ExpirtionDateLabel = New System.Windows.Forms.Label()
        Me.Label4 = New System.Windows.Forms.Label()
        Me.CreatePrescription = New System.Windows.Forms.Button()
        Me.PrescriptionType = New System.Windows.Forms.ComboBox()
        Me.Label3 = New System.Windows.Forms.Label()
        Me.Feedback = New System.Windows.Forms.CheckBox()
        Me.Label2 = New System.Windows.Forms.Label()
        Me.PatientID = New System.Windows.Forms.TextBox()
        Me.Label1 = New System.Windows.Forms.Label()
        Me.VisionLabel = New System.Windows.Forms.Label()
        Me.PrescriptionContent = New System.Windows.Forms.RichTextBox()
        Me.TabPage2 = New System.Windows.Forms.TabPage()
        Me.GetPrescriptionStatus = New System.Windows.Forms.Button()
        Me.GetPrescriptionPatientID = New System.Windows.Forms.TextBox()
        Me.Label8 = New System.Windows.Forms.Label()
        Me.GetPrescriptionDate = New System.Windows.Forms.TextBox()
        Me.Label7 = New System.Windows.Forms.Label()
        Me.RequestFeedback = New System.Windows.Forms.CheckBox()
        Me.Label6 = New System.Windows.Forms.Label()
        Me.GetPrescriptionContent = New System.Windows.Forms.RichTextBox()
        Me.RevokePrescription = New System.Windows.Forms.Button()
        Me.GetPrescription = New System.Windows.Forms.Button()
        Me.RIDTextBox = New System.Windows.Forms.TextBox()
        Me.Label5 = New System.Windows.Forms.Label()
        Me.TabPage3 = New System.Windows.Forms.TabPage()
        Me.SetPrivKeyPwdButton = New System.Windows.Forms.Button()
        Me.ListFeedbacksUnread = New System.Windows.Forms.CheckBox()
        Me.ListFeedbacksResult = New System.Windows.Forms.RichTextBox()
        Me.ListFeedbacks = New System.Windows.Forms.Button()
        Me.TabPage4 = New System.Windows.Forms.TabPage()
        Me.ListOpenPrescriptionsNextButton = New System.Windows.Forms.Button()
        Me.ListOpenPrescriptionsPreviousButton = New System.Windows.Forms.Button()
        Me.ListOpenRidsPageLabel = New System.Windows.Forms.Label()
        Me.ListPrescrPatientId = New System.Windows.Forms.TextBox()
        Me.Label18 = New System.Windows.Forms.Label()
        Me.ListOpenPrescriptionResult = New System.Windows.Forms.RichTextBox()
        Me.ListOpenPrescriptionButton = New System.Windows.Forms.Button()
        Me.TabPage5 = New System.Windows.Forms.TabPage()
        Me.Label10 = New System.Windows.Forms.Label()
        Me.txtNotification = New System.Windows.Forms.RichTextBox()
        Me.NotificationExecutorID = New System.Windows.Forms.TextBox()
        Me.Label12 = New System.Windows.Forms.Label()
        Me.NotificationPatientID = New System.Windows.Forms.TextBox()
        Me.Label11 = New System.Windows.Forms.Label()
        Me.SendNotification = New System.Windows.Forms.Button()
        Me.Label9 = New System.Windows.Forms.Label()
        Me.txtPrescriptionNotification = New System.Windows.Forms.RichTextBox()
        Me.TabPage7 = New System.Windows.Forms.TabPage()
        Me.ListRidsHistoryCheckBox = New System.Windows.Forms.CheckBox()
        Me.ListRidsHistoryButton = New System.Windows.Forms.Button()
        Me.ListRidsHistoryRichTextBox = New System.Windows.Forms.RichTextBox()
        Me.ListRidsHistoryTextBox = New System.Windows.Forms.TextBox()
        Me.ListRidsHistoryPatientIDLabel = New System.Windows.Forms.Label()
        Me.ListRidsHistoryPageLabel = New System.Windows.Forms.Label()
        Me.ListRidsHistoryNextPageButton = New System.Windows.Forms.Button()
        Me.ListRidsHistoryPreviousPageButton = New System.Windows.Forms.Button()
        Me.TabPage8 = New System.Windows.Forms.TabPage()
        Me.PutVisionRidLabel = New System.Windows.Forms.Label()
        Me.PutVisionRidTextBox = New System.Windows.Forms.TextBox()
        Me.PutVisionButton = New System.Windows.Forms.Button()
        Me.PutVisionTextBox = New System.Windows.Forms.TextBox()
        Me.PutVisionLabel = New System.Windows.Forms.Label()
        Me.ListRidsHistoryResult = New System.Windows.Forms.RichTextBox()
        Me.PatientIdTextBox = New System.Windows.Forms.TextBox()
        Me.PatientIdLabel = New System.Windows.Forms.Label()
        Me.TabControl1.SuspendLayout()
        Me.TabPage6.SuspendLayout()
        Me.TabPage1.SuspendLayout()
        Me.TabPage2.SuspendLayout()
        Me.TabPage3.SuspendLayout()
        Me.TabPage4.SuspendLayout()
        Me.TabPage5.SuspendLayout()
        Me.TabPage7.SuspendLayout()
        Me.TabPage8.SuspendLayout()
        Me.SuspendLayout()
        '
        'TabControl1
        '
        Me.TabControl1.Controls.Add(Me.TabPage6)
        Me.TabControl1.Controls.Add(Me.TabPage1)
        Me.TabControl1.Controls.Add(Me.TabPage2)
        Me.TabControl1.Controls.Add(Me.TabPage3)
        Me.TabControl1.Controls.Add(Me.TabPage4)
        Me.TabControl1.Controls.Add(Me.TabPage5)
        Me.TabControl1.Controls.Add(Me.TabPage7)
        Me.TabControl1.Controls.Add(Me.TabPage8)
        Me.TabControl1.Location = New System.Drawing.Point(-1, 2)
        Me.TabControl1.Name = "TabControl1"
        Me.TabControl1.SelectedIndex = 0
        Me.TabControl1.Size = New System.Drawing.Size(787, 472)
        Me.TabControl1.TabIndex = 0
        '
        'TabPage6
        '
        Me.TabPage6.Controls.Add(Me.btnCreateFallbackSession)
        Me.TabPage6.Controls.Add(Me.LoginButton)
        Me.TabPage6.Controls.Add(Me.Label13)
        Me.TabPage6.Controls.Add(Me.LoginSAMLResponse)
        Me.TabPage6.Location = New System.Drawing.Point(4, 22)
        Me.TabPage6.Name = "TabPage6"
        Me.TabPage6.Size = New System.Drawing.Size(779, 446)
        Me.TabPage6.TabIndex = 5
        Me.TabPage6.Text = "Create Session"
        Me.TabPage6.UseVisualStyleBackColor = True
        '
        'btnCreateFallbackSession
        '
        Me.btnCreateFallbackSession.Location = New System.Drawing.Point(189, 0)
        Me.btnCreateFallbackSession.Name = "btnCreateFallbackSession"
        Me.btnCreateFallbackSession.Size = New System.Drawing.Size(139, 23)
        Me.btnCreateFallbackSession.TabIndex = 9
        Me.btnCreateFallbackSession.Text = "Create Fallback Session"
        Me.btnCreateFallbackSession.UseVisualStyleBackColor = True
        '
        'LoginButton
        '
        Me.LoginButton.Location = New System.Drawing.Point(3, 0)
        Me.LoginButton.Name = "LoginButton"
        Me.LoginButton.Size = New System.Drawing.Size(139, 23)
        Me.LoginButton.TabIndex = 8
        Me.LoginButton.Text = "Create Session"
        Me.LoginButton.UseVisualStyleBackColor = True
        '
        'Label13
        '
        Me.Label13.AutoSize = True
        Me.Label13.Location = New System.Drawing.Point(7, 33)
        Me.Label13.Name = "Label13"
        Me.Label13.Size = New System.Drawing.Size(87, 13)
        Me.Label13.TabIndex = 7
        Me.Label13.Text = "SAML Response"
        '
        'LoginSAMLResponse
        '
        Me.LoginSAMLResponse.BackColor = System.Drawing.SystemColors.Window
        Me.LoginSAMLResponse.Location = New System.Drawing.Point(1, 49)
        Me.LoginSAMLResponse.Name = "LoginSAMLResponse"
        Me.LoginSAMLResponse.ReadOnly = True
        Me.LoginSAMLResponse.Size = New System.Drawing.Size(776, 401)
        Me.LoginSAMLResponse.TabIndex = 6
        Me.LoginSAMLResponse.Text = ""
        '
        'TabPage1
        '
        Me.TabPage1.Controls.Add(Me.BulkAmountTextBox)
        Me.TabPage1.Controls.Add(Me.BulkAmountLabel)
        Me.TabPage1.Controls.Add(Me.BulkLabel)
        Me.TabPage1.Controls.Add(Me.BulkCheckBox)
        Me.TabPage1.Controls.Add(Me.ExpirationDate)
        Me.TabPage1.Controls.Add(Me.SelectedExpirationDateLabel)
        Me.TabPage1.Controls.Add(Me.VisionTextBox)
        Me.TabPage1.Controls.Add(Me.ExpirtionDateLabel)
        Me.TabPage1.Controls.Add(Me.Label4)
        Me.TabPage1.Controls.Add(Me.CreatePrescription)
        Me.TabPage1.Controls.Add(Me.PrescriptionType)
        Me.TabPage1.Controls.Add(Me.Label3)
        Me.TabPage1.Controls.Add(Me.Feedback)
        Me.TabPage1.Controls.Add(Me.Label2)
        Me.TabPage1.Controls.Add(Me.PatientID)
        Me.TabPage1.Controls.Add(Me.Label1)
        Me.TabPage1.Controls.Add(Me.VisionLabel)
        Me.TabPage1.Controls.Add(Me.PrescriptionContent)
        Me.TabPage1.Location = New System.Drawing.Point(4, 22)
        Me.TabPage1.Name = "TabPage1"
        Me.TabPage1.Padding = New System.Windows.Forms.Padding(3)
        Me.TabPage1.Size = New System.Drawing.Size(779, 446)
        Me.TabPage1.TabIndex = 0
        Me.TabPage1.Text = "Create Prescription"
        Me.TabPage1.UseVisualStyleBackColor = True
        '
        'BulkAmountTextBox
        '
        Me.BulkAmountTextBox.Location = New System.Drawing.Point(716, 29)
        Me.BulkAmountTextBox.Name = "BulkAmountTextBox"
        Me.BulkAmountTextBox.Size = New System.Drawing.Size(23, 20)
        Me.BulkAmountTextBox.TabIndex = 17
        Me.BulkAmountTextBox.Text = "3"
        '
        'BulkAmountLabel
        '
        Me.BulkAmountLabel.AutoSize = True
        Me.BulkAmountLabel.Location = New System.Drawing.Point(666, 32)
        Me.BulkAmountLabel.Name = "BulkAmountLabel"
        Me.BulkAmountLabel.Size = New System.Drawing.Size(43, 13)
        Me.BulkAmountLabel.TabIndex = 16
        Me.BulkAmountLabel.Text = "Amount"
        '
        'BulkLabel
        '
        Me.BulkLabel.AutoSize = True
        Me.BulkLabel.Location = New System.Drawing.Point(681, 10)
        Me.BulkLabel.Name = "BulkLabel"
        Me.BulkLabel.Size = New System.Drawing.Size(28, 13)
        Me.BulkLabel.TabIndex = 15
        Me.BulkLabel.Text = "Bulk"
        '
        'BulkCheckBox
        '
        Me.BulkCheckBox.AutoSize = True
        Me.BulkCheckBox.Location = New System.Drawing.Point(716, 10)
        Me.BulkCheckBox.Name = "BulkCheckBox"
        Me.BulkCheckBox.Size = New System.Drawing.Size(15, 14)
        Me.BulkCheckBox.TabIndex = 14
        Me.BulkCheckBox.UseVisualStyleBackColor = True
        '
        'ExpirationDate
        '
        Me.ExpirationDate.AllowDrop = True
        Me.ExpirationDate.BackColor = System.Drawing.Color.White
        Me.ExpirationDate.Cursor = System.Windows.Forms.Cursors.Arrow
        Me.ExpirationDate.Location = New System.Drawing.Point(292, 36)
        Me.ExpirationDate.Name = "ExpirationDate"
        Me.ExpirationDate.TabIndex = 10
        '
        'SelectedExpirationDateLabel
        '
        Me.SelectedExpirationDateLabel.AutoSize = True
        Me.SelectedExpirationDateLabel.Location = New System.Drawing.Point(294, 36)
        Me.SelectedExpirationDateLabel.Name = "SelectedExpirationDateLabel"
        Me.SelectedExpirationDateLabel.Size = New System.Drawing.Size(0, 13)
        Me.SelectedExpirationDateLabel.TabIndex = 11
        '
        'VisionTextBox
        '
        Me.VisionTextBox.Location = New System.Drawing.Point(446, 29)
        Me.VisionTextBox.Name = "VisionTextBox"
        Me.VisionTextBox.Size = New System.Drawing.Size(181, 20)
        Me.VisionTextBox.TabIndex = 13
        '
        'ExpirtionDateLabel
        '
        Me.ExpirtionDateLabel.AutoSize = True
        Me.ExpirtionDateLabel.Location = New System.Drawing.Point(210, 36)
        Me.ExpirtionDateLabel.Name = "ExpirtionDateLabel"
        Me.ExpirtionDateLabel.Size = New System.Drawing.Size(77, 13)
        Me.ExpirtionDateLabel.TabIndex = 9
        Me.ExpirtionDateLabel.Text = "Expiration date"
        '
        'Label4
        '
        Me.Label4.AutoSize = True
        Me.Label4.Location = New System.Drawing.Point(385, 9)
        Me.Label4.Name = "Label4"
        Me.Label4.Size = New System.Drawing.Size(55, 13)
        Me.Label4.TabIndex = 8
        Me.Label4.Text = "Feedback"
        '
        'CreatePrescription
        '
        Me.CreatePrescription.Location = New System.Drawing.Point(704, 423)
        Me.CreatePrescription.Name = "CreatePrescription"
        Me.CreatePrescription.Size = New System.Drawing.Size(75, 23)
        Me.CreatePrescription.TabIndex = 7
        Me.CreatePrescription.Text = "Create"
        Me.CreatePrescription.UseVisualStyleBackColor = True
        '
        'PrescriptionType
        '
        Me.PrescriptionType.DisplayMember = "P0,P1"
        Me.PrescriptionType.FormattingEnabled = True
        Me.PrescriptionType.Items.AddRange(New Object() {"P0", "P1", "P2"})
        Me.PrescriptionType.Location = New System.Drawing.Point(294, 4)
        Me.PrescriptionType.Name = "PrescriptionType"
        Me.PrescriptionType.Size = New System.Drawing.Size(40, 21)
        Me.PrescriptionType.TabIndex = 6
        Me.PrescriptionType.Text = "P0"
        '
        'Label3
        '
        Me.Label3.AutoSize = True
        Me.Label3.Location = New System.Drawing.Point(9, 36)
        Me.Label3.Name = "Label3"
        Me.Label3.Size = New System.Drawing.Size(102, 13)
        Me.Label3.TabIndex = 5
        Me.Label3.Text = "Prescription Content"
        '
        'Feedback
        '
        Me.Feedback.AutoSize = True
        Me.Feedback.Checked = True
        Me.Feedback.CheckState = System.Windows.Forms.CheckState.Checked
        Me.Feedback.Location = New System.Drawing.Point(446, 8)
        Me.Feedback.Name = "Feedback"
        Me.Feedback.Size = New System.Drawing.Size(15, 14)
        Me.Feedback.TabIndex = 4
        Me.Feedback.UseVisualStyleBackColor = True
        '
        'Label2
        '
        Me.Label2.AutoSize = True
        Me.Label2.Location = New System.Drawing.Point(199, 7)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(89, 13)
        Me.Label2.TabIndex = 3
        Me.Label2.Text = "Prescription Type"
        '
        'PatientID
        '
        Me.PatientID.Location = New System.Drawing.Point(67, 3)
        Me.PatientID.Name = "PatientID"
        Me.PatientID.Size = New System.Drawing.Size(81, 20)
        Me.PatientID.TabIndex = 2
        Me.PatientID.Text = "72081061175"
        '
        'Label1
        '
        Me.Label1.AutoSize = True
        Me.Label1.Location = New System.Drawing.Point(9, 6)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(54, 13)
        Me.Label1.TabIndex = 1
        Me.Label1.Text = "Patient ID"
        '
        'VisionLabel
        '
        Me.VisionLabel.AutoSize = True
        Me.VisionLabel.Location = New System.Drawing.Point(403, 32)
        Me.VisionLabel.Name = "VisionLabel"
        Me.VisionLabel.Size = New System.Drawing.Size(35, 13)
        Me.VisionLabel.TabIndex = 12
        Me.VisionLabel.Text = "Vision"
        '
        'PrescriptionContent
        '
        Me.PrescriptionContent.Location = New System.Drawing.Point(3, 52)
        Me.PrescriptionContent.Name = "PrescriptionContent"
        Me.PrescriptionContent.Size = New System.Drawing.Size(776, 365)
        Me.PrescriptionContent.TabIndex = 0
        Me.PrescriptionContent.Text = resources.GetString("PrescriptionContent.Text")
        '
        'TabPage2
        '
        Me.TabPage2.Controls.Add(Me.GetPrescriptionStatus)
        Me.TabPage2.Controls.Add(Me.GetPrescriptionPatientID)
        Me.TabPage2.Controls.Add(Me.Label8)
        Me.TabPage2.Controls.Add(Me.GetPrescriptionDate)
        Me.TabPage2.Controls.Add(Me.Label7)
        Me.TabPage2.Controls.Add(Me.RequestFeedback)
        Me.TabPage2.Controls.Add(Me.Label6)
        Me.TabPage2.Controls.Add(Me.GetPrescriptionContent)
        Me.TabPage2.Controls.Add(Me.RevokePrescription)
        Me.TabPage2.Controls.Add(Me.GetPrescription)
        Me.TabPage2.Controls.Add(Me.RIDTextBox)
        Me.TabPage2.Controls.Add(Me.Label5)
        Me.TabPage2.Location = New System.Drawing.Point(4, 22)
        Me.TabPage2.Name = "TabPage2"
        Me.TabPage2.Padding = New System.Windows.Forms.Padding(3)
        Me.TabPage2.Size = New System.Drawing.Size(779, 446)
        Me.TabPage2.TabIndex = 1
        Me.TabPage2.Text = "Get Prescription"
        Me.TabPage2.UseVisualStyleBackColor = True
        '
        'GetPrescriptionStatus
        '
        Me.GetPrescriptionStatus.Location = New System.Drawing.Point(346, 0)
        Me.GetPrescriptionStatus.Name = "GetPrescriptionStatus"
        Me.GetPrescriptionStatus.Size = New System.Drawing.Size(75, 23)
        Me.GetPrescriptionStatus.TabIndex = 17
        Me.GetPrescriptionStatus.Text = "Get status"
        Me.GetPrescriptionStatus.UseVisualStyleBackColor = True
        '
        'GetPrescriptionPatientID
        '
        Me.GetPrescriptionPatientID.BackColor = System.Drawing.SystemColors.Window
        Me.GetPrescriptionPatientID.Location = New System.Drawing.Point(280, 30)
        Me.GetPrescriptionPatientID.Name = "GetPrescriptionPatientID"
        Me.GetPrescriptionPatientID.ReadOnly = True
        Me.GetPrescriptionPatientID.Size = New System.Drawing.Size(100, 20)
        Me.GetPrescriptionPatientID.TabIndex = 16
        '
        'Label8
        '
        Me.Label8.AutoSize = True
        Me.Label8.Location = New System.Drawing.Point(222, 33)
        Me.Label8.Name = "Label8"
        Me.Label8.Size = New System.Drawing.Size(54, 13)
        Me.Label8.TabIndex = 15
        Me.Label8.Text = "Patient ID"
        '
        'GetPrescriptionDate
        '
        Me.GetPrescriptionDate.BackColor = System.Drawing.SystemColors.Window
        Me.GetPrescriptionDate.Location = New System.Drawing.Point(87, 30)
        Me.GetPrescriptionDate.Name = "GetPrescriptionDate"
        Me.GetPrescriptionDate.ReadOnly = True
        Me.GetPrescriptionDate.Size = New System.Drawing.Size(100, 20)
        Me.GetPrescriptionDate.TabIndex = 14
        '
        'Label7
        '
        Me.Label7.AutoSize = True
        Me.Label7.Location = New System.Drawing.Point(11, 33)
        Me.Label7.Name = "Label7"
        Me.Label7.Size = New System.Drawing.Size(72, 13)
        Me.Label7.TabIndex = 13
        Me.Label7.Text = "Creation Date"
        '
        'RequestFeedback
        '
        Me.RequestFeedback.AutoSize = True
        Me.RequestFeedback.Location = New System.Drawing.Point(543, 2)
        Me.RequestFeedback.Name = "RequestFeedback"
        Me.RequestFeedback.Size = New System.Drawing.Size(117, 17)
        Me.RequestFeedback.TabIndex = 12
        Me.RequestFeedback.Text = "Request Feedback"
        Me.RequestFeedback.UseVisualStyleBackColor = True
        '
        'Label6
        '
        Me.Label6.AutoSize = True
        Me.Label6.Location = New System.Drawing.Point(7, 53)
        Me.Label6.Name = "Label6"
        Me.Label6.Size = New System.Drawing.Size(102, 13)
        Me.Label6.TabIndex = 11
        Me.Label6.Text = "Prescription Content"
        '
        'GetPrescriptionContent
        '
        Me.GetPrescriptionContent.BackColor = System.Drawing.SystemColors.Window
        Me.GetPrescriptionContent.Location = New System.Drawing.Point(1, 69)
        Me.GetPrescriptionContent.Name = "GetPrescriptionContent"
        Me.GetPrescriptionContent.ReadOnly = True
        Me.GetPrescriptionContent.Size = New System.Drawing.Size(776, 377)
        Me.GetPrescriptionContent.TabIndex = 10
        Me.GetPrescriptionContent.Text = ""
        '
        'RevokePrescription
        '
        Me.RevokePrescription.Location = New System.Drawing.Point(265, 0)
        Me.RevokePrescription.Name = "RevokePrescription"
        Me.RevokePrescription.Size = New System.Drawing.Size(75, 23)
        Me.RevokePrescription.TabIndex = 9
        Me.RevokePrescription.Text = "Revoke"
        Me.RevokePrescription.UseVisualStyleBackColor = True
        '
        'GetPrescription
        '
        Me.GetPrescription.Location = New System.Drawing.Point(184, 0)
        Me.GetPrescription.Name = "GetPrescription"
        Me.GetPrescription.Size = New System.Drawing.Size(75, 23)
        Me.GetPrescription.TabIndex = 8
        Me.GetPrescription.Text = "Get"
        Me.GetPrescription.UseVisualStyleBackColor = True
        '
        'RIDTextBox
        '
        Me.RIDTextBox.Location = New System.Drawing.Point(41, 0)
        Me.RIDTextBox.Name = "RIDTextBox"
        Me.RIDTextBox.Size = New System.Drawing.Size(100, 20)
        Me.RIDTextBox.TabIndex = 3
        Me.RIDTextBox.Text = "BEPPA1B2C3D4"
        '
        'Label5
        '
        Me.Label5.AutoSize = True
        Me.Label5.Location = New System.Drawing.Point(9, 3)
        Me.Label5.Name = "Label5"
        Me.Label5.Size = New System.Drawing.Size(26, 13)
        Me.Label5.TabIndex = 2
        Me.Label5.Text = "RID"
        '
        'TabPage3
        '
        Me.TabPage3.Controls.Add(Me.SetPrivKeyPwdButton)
        Me.TabPage3.Controls.Add(Me.ListFeedbacksUnread)
        Me.TabPage3.Controls.Add(Me.ListFeedbacksResult)
        Me.TabPage3.Controls.Add(Me.ListFeedbacks)
        Me.TabPage3.Location = New System.Drawing.Point(4, 22)
        Me.TabPage3.Name = "TabPage3"
        Me.TabPage3.Size = New System.Drawing.Size(779, 446)
        Me.TabPage3.TabIndex = 2
        Me.TabPage3.Text = "List Feedbacks"
        Me.TabPage3.UseVisualStyleBackColor = True
        '
        'SetPrivKeyPwdButton
        '
        Me.SetPrivKeyPwdButton.Location = New System.Drawing.Point(504, 5)
        Me.SetPrivKeyPwdButton.Name = "SetPrivKeyPwdButton"
        Me.SetPrivKeyPwdButton.Size = New System.Drawing.Size(169, 23)
        Me.SetPrivKeyPwdButton.TabIndex = 13
        Me.SetPrivKeyPwdButton.Text = "[Set Private Key Password]"
        Me.SetPrivKeyPwdButton.UseVisualStyleBackColor = True
        '
        'ListFeedbacksUnread
        '
        Me.ListFeedbacksUnread.AutoSize = True
        Me.ListFeedbacksUnread.Checked = True
        Me.ListFeedbacksUnread.CheckState = System.Windows.Forms.CheckState.Checked
        Me.ListFeedbacksUnread.Location = New System.Drawing.Point(688, 9)
        Me.ListFeedbacksUnread.Name = "ListFeedbacksUnread"
        Me.ListFeedbacksUnread.Size = New System.Drawing.Size(85, 17)
        Me.ListFeedbacksUnread.TabIndex = 12
        Me.ListFeedbacksUnread.Text = "Unread Only"
        Me.ListFeedbacksUnread.UseVisualStyleBackColor = True
        '
        'ListFeedbacksResult
        '
        Me.ListFeedbacksResult.BackColor = System.Drawing.SystemColors.Window
        Me.ListFeedbacksResult.Location = New System.Drawing.Point(1, 32)
        Me.ListFeedbacksResult.Name = "ListFeedbacksResult"
        Me.ListFeedbacksResult.ReadOnly = True
        Me.ListFeedbacksResult.Size = New System.Drawing.Size(776, 414)
        Me.ListFeedbacksResult.TabIndex = 11
        Me.ListFeedbacksResult.Text = ""
        '
        'ListFeedbacks
        '
        Me.ListFeedbacks.Location = New System.Drawing.Point(3, 3)
        Me.ListFeedbacks.Name = "ListFeedbacks"
        Me.ListFeedbacks.Size = New System.Drawing.Size(97, 23)
        Me.ListFeedbacks.TabIndex = 9
        Me.ListFeedbacks.Text = "List Feedbacks"
        Me.ListFeedbacks.UseVisualStyleBackColor = True
        '
        'TabPage4
        '
        Me.TabPage4.Controls.Add(Me.ListOpenPrescriptionsNextButton)
        Me.TabPage4.Controls.Add(Me.ListOpenPrescriptionsPreviousButton)
        Me.TabPage4.Controls.Add(Me.ListOpenRidsPageLabel)
        Me.TabPage4.Controls.Add(Me.ListPrescrPatientId)
        Me.TabPage4.Controls.Add(Me.Label18)
        Me.TabPage4.Controls.Add(Me.ListOpenPrescriptionResult)
        Me.TabPage4.Controls.Add(Me.ListOpenPrescriptionButton)
        Me.TabPage4.Location = New System.Drawing.Point(4, 22)
        Me.TabPage4.Name = "TabPage4"
        Me.TabPage4.Size = New System.Drawing.Size(779, 446)
        Me.TabPage4.TabIndex = 3
        Me.TabPage4.Text = "List Open Rids"
        Me.TabPage4.UseVisualStyleBackColor = True
        '
        'ListOpenPrescriptionsNextButton
        '
        Me.ListOpenPrescriptionsNextButton.Location = New System.Drawing.Point(351, 3)
        Me.ListOpenPrescriptionsNextButton.Name = "ListOpenPrescriptionsNextButton"
        Me.ListOpenPrescriptionsNextButton.Size = New System.Drawing.Size(28, 23)
        Me.ListOpenPrescriptionsNextButton.TabIndex = 21
        Me.ListOpenPrescriptionsNextButton.Text = ">>"
        Me.ListOpenPrescriptionsNextButton.UseVisualStyleBackColor = True
        '
        'ListOpenPrescriptionsPreviousButton
        '
        Me.ListOpenPrescriptionsPreviousButton.Location = New System.Drawing.Point(316, 3)
        Me.ListOpenPrescriptionsPreviousButton.Name = "ListOpenPrescriptionsPreviousButton"
        Me.ListOpenPrescriptionsPreviousButton.Size = New System.Drawing.Size(28, 23)
        Me.ListOpenPrescriptionsPreviousButton.TabIndex = 20
        Me.ListOpenPrescriptionsPreviousButton.Text = "<<"
        Me.ListOpenPrescriptionsPreviousButton.UseVisualStyleBackColor = True
        '
        'ListOpenRidsPageLabel
        '
        Me.ListOpenRidsPageLabel.AutoSize = True
        Me.ListOpenRidsPageLabel.Location = New System.Drawing.Point(393, 8)
        Me.ListOpenRidsPageLabel.Name = "ListOpenRidsPageLabel"
        Me.ListOpenRidsPageLabel.Size = New System.Drawing.Size(35, 13)
        Me.ListOpenRidsPageLabel.TabIndex = 19
        Me.ListOpenRidsPageLabel.Text = "Page:"
        '
        'ListPrescrPatientId
        '
        Me.ListPrescrPatientId.Location = New System.Drawing.Point(65, 6)
        Me.ListPrescrPatientId.Name = "ListPrescrPatientId"
        Me.ListPrescrPatientId.Size = New System.Drawing.Size(81, 20)
        Me.ListPrescrPatientId.TabIndex = 14
        Me.ListPrescrPatientId.Text = "84071230581"
        '
        'Label18
        '
        Me.Label18.AutoSize = True
        Me.Label18.Location = New System.Drawing.Point(7, 9)
        Me.Label18.Name = "Label18"
        Me.Label18.Size = New System.Drawing.Size(54, 13)
        Me.Label18.TabIndex = 13
        Me.Label18.Text = "Patient ID"
        '
        'ListOpenPrescriptionResult
        '
        Me.ListOpenPrescriptionResult.BackColor = System.Drawing.SystemColors.Window
        Me.ListOpenPrescriptionResult.CausesValidation = False
        Me.ListOpenPrescriptionResult.Location = New System.Drawing.Point(1, 32)
        Me.ListOpenPrescriptionResult.Name = "ListOpenPrescriptionResult"
        Me.ListOpenPrescriptionResult.ReadOnly = True
        Me.ListOpenPrescriptionResult.Size = New System.Drawing.Size(776, 414)
        Me.ListOpenPrescriptionResult.TabIndex = 12
        Me.ListOpenPrescriptionResult.Text = ""
        '
        'ListOpenPrescriptionButton
        '
        Me.ListOpenPrescriptionButton.Location = New System.Drawing.Point(164, 3)
        Me.ListOpenPrescriptionButton.Name = "ListOpenPrescriptionButton"
        Me.ListOpenPrescriptionButton.Size = New System.Drawing.Size(129, 23)
        Me.ListOpenPrescriptionButton.TabIndex = 10
        Me.ListOpenPrescriptionButton.Text = "List Open Rids"
        Me.ListOpenPrescriptionButton.UseVisualStyleBackColor = True
        '
        'TabPage5
        '
        Me.TabPage5.Controls.Add(Me.Label10)
        Me.TabPage5.Controls.Add(Me.txtNotification)
        Me.TabPage5.Controls.Add(Me.NotificationExecutorID)
        Me.TabPage5.Controls.Add(Me.Label12)
        Me.TabPage5.Controls.Add(Me.NotificationPatientID)
        Me.TabPage5.Controls.Add(Me.Label11)
        Me.TabPage5.Controls.Add(Me.SendNotification)
        Me.TabPage5.Controls.Add(Me.Label9)
        Me.TabPage5.Controls.Add(Me.txtPrescriptionNotification)
        Me.TabPage5.Location = New System.Drawing.Point(4, 22)
        Me.TabPage5.Name = "TabPage5"
        Me.TabPage5.Size = New System.Drawing.Size(779, 446)
        Me.TabPage5.TabIndex = 4
        Me.TabPage5.Text = "Create Notification"
        Me.TabPage5.UseVisualStyleBackColor = True
        '
        'Label10
        '
        Me.Label10.AutoSize = True
        Me.Label10.Location = New System.Drawing.Point(3, 43)
        Me.Label10.Name = "Label10"
        Me.Label10.Size = New System.Drawing.Size(50, 13)
        Me.Label10.TabIndex = 22
        Me.Label10.Text = "Message"
        '
        'txtNotification
        '
        Me.txtNotification.Location = New System.Drawing.Point(0, 59)
        Me.txtNotification.Name = "txtNotification"
        Me.txtNotification.Size = New System.Drawing.Size(776, 91)
        Me.txtNotification.TabIndex = 21
        Me.txtNotification.Text = "This is a notification"
        '
        'NotificationExecutorID
        '
        Me.NotificationExecutorID.Location = New System.Drawing.Point(276, 6)
        Me.NotificationExecutorID.Name = "NotificationExecutorID"
        Me.NotificationExecutorID.Size = New System.Drawing.Size(81, 20)
        Me.NotificationExecutorID.TabIndex = 20
        Me.NotificationExecutorID.Text = "66666219"
        '
        'Label12
        '
        Me.Label12.AutoSize = True
        Me.Label12.Location = New System.Drawing.Point(209, 9)
        Me.Label12.Name = "Label12"
        Me.Label12.Size = New System.Drawing.Size(63, 13)
        Me.Label12.TabIndex = 19
        Me.Label12.Text = "Executor ID"
        '
        'NotificationPatientID
        '
        Me.NotificationPatientID.Location = New System.Drawing.Point(67, 6)
        Me.NotificationPatientID.Name = "NotificationPatientID"
        Me.NotificationPatientID.Size = New System.Drawing.Size(81, 20)
        Me.NotificationPatientID.TabIndex = 18
        Me.NotificationPatientID.Text = "84071230581"
        '
        'Label11
        '
        Me.Label11.AutoSize = True
        Me.Label11.Location = New System.Drawing.Point(9, 9)
        Me.Label11.Name = "Label11"
        Me.Label11.Size = New System.Drawing.Size(54, 13)
        Me.Label11.TabIndex = 17
        Me.Label11.Text = "Patient ID"
        '
        'SendNotification
        '
        Me.SendNotification.Location = New System.Drawing.Point(704, 423)
        Me.SendNotification.Name = "SendNotification"
        Me.SendNotification.Size = New System.Drawing.Size(75, 23)
        Me.SendNotification.TabIndex = 16
        Me.SendNotification.Text = "Send"
        Me.SendNotification.UseVisualStyleBackColor = True
        '
        'Label9
        '
        Me.Label9.AutoSize = True
        Me.Label9.Location = New System.Drawing.Point(3, 160)
        Me.Label9.Name = "Label9"
        Me.Label9.Size = New System.Drawing.Size(62, 13)
        Me.Label9.TabIndex = 15
        Me.Label9.Text = "Prescription"
        '
        'txtPrescriptionNotification
        '
        Me.txtPrescriptionNotification.Location = New System.Drawing.Point(0, 176)
        Me.txtPrescriptionNotification.Name = "txtPrescriptionNotification"
        Me.txtPrescriptionNotification.Size = New System.Drawing.Size(776, 241)
        Me.txtPrescriptionNotification.TabIndex = 14
        Me.txtPrescriptionNotification.Text = resources.GetString("txtPrescriptionNotification.Text")
        '
        'TabPage7
        '
        Me.TabPage7.Controls.Add(Me.ListRidsHistoryCheckBox)
        Me.TabPage7.Controls.Add(Me.ListRidsHistoryButton)
        Me.TabPage7.Controls.Add(Me.ListRidsHistoryRichTextBox)
        Me.TabPage7.Controls.Add(Me.ListRidsHistoryTextBox)
        Me.TabPage7.Controls.Add(Me.ListRidsHistoryPatientIDLabel)
        Me.TabPage7.Controls.Add(Me.ListRidsHistoryPageLabel)
        Me.TabPage7.Controls.Add(Me.ListRidsHistoryNextPageButton)
        Me.TabPage7.Controls.Add(Me.ListRidsHistoryPreviousPageButton)
        Me.TabPage7.Location = New System.Drawing.Point(4, 22)
        Me.TabPage7.Name = "TabPage7"
        Me.TabPage7.Size = New System.Drawing.Size(779, 446)
        Me.TabPage7.TabIndex = 3
        Me.TabPage7.Text = "List Rids History"
        Me.TabPage7.UseVisualStyleBackColor = True
        '
        'ListRidsHistoryCheckBox
        '
        Me.ListRidsHistoryCheckBox.AutoSize = True
        Me.ListRidsHistoryCheckBox.Checked = True
        Me.ListRidsHistoryCheckBox.CheckState = System.Windows.Forms.CheckState.Checked
        Me.ListRidsHistoryCheckBox.Location = New System.Drawing.Point(496, 6)
        Me.ListRidsHistoryCheckBox.Name = "ListRidsHistoryCheckBox"
        Me.ListRidsHistoryCheckBox.Size = New System.Drawing.Size(132, 17)
        Me.ListRidsHistoryCheckBox.TabIndex = 23
        Me.ListRidsHistoryCheckBox.Text = "Search active partition"
        Me.ListRidsHistoryCheckBox.UseVisualStyleBackColor = True
        '
        'ListRidsHistoryButton
        '
        Me.ListRidsHistoryButton.Location = New System.Drawing.Point(156, 4)
        Me.ListRidsHistoryButton.Name = "ListRidsHistoryButton"
        Me.ListRidsHistoryButton.Size = New System.Drawing.Size(137, 23)
        Me.ListRidsHistoryButton.TabIndex = 22
        Me.ListRidsHistoryButton.Text = "List Rids History"
        Me.ListRidsHistoryButton.UseVisualStyleBackColor = True
        '
        'ListRidsHistoryRichTextBox
        '
        Me.ListRidsHistoryRichTextBox.BackColor = System.Drawing.SystemColors.Window
        Me.ListRidsHistoryRichTextBox.CausesValidation = False
        Me.ListRidsHistoryRichTextBox.Location = New System.Drawing.Point(1, 35)
        Me.ListRidsHistoryRichTextBox.Name = "ListRidsHistoryRichTextBox"
        Me.ListRidsHistoryRichTextBox.ReadOnly = True
        Me.ListRidsHistoryRichTextBox.Size = New System.Drawing.Size(776, 404)
        Me.ListRidsHistoryRichTextBox.TabIndex = 21
        Me.ListRidsHistoryRichTextBox.Text = ""
        '
        'ListRidsHistoryTextBox
        '
        Me.ListRidsHistoryTextBox.Location = New System.Drawing.Point(69, 6)
        Me.ListRidsHistoryTextBox.Name = "ListRidsHistoryTextBox"
        Me.ListRidsHistoryTextBox.Size = New System.Drawing.Size(81, 20)
        Me.ListRidsHistoryTextBox.TabIndex = 20
        Me.ListRidsHistoryTextBox.Text = "84071230581"
        '
        'ListRidsHistoryPatientIDLabel
        '
        Me.ListRidsHistoryPatientIDLabel.AutoSize = True
        Me.ListRidsHistoryPatientIDLabel.Location = New System.Drawing.Point(9, 9)
        Me.ListRidsHistoryPatientIDLabel.Name = "ListRidsHistoryPatientIDLabel"
        Me.ListRidsHistoryPatientIDLabel.Size = New System.Drawing.Size(54, 13)
        Me.ListRidsHistoryPatientIDLabel.TabIndex = 19
        Me.ListRidsHistoryPatientIDLabel.Text = "Patient ID"
        '
        'ListRidsHistoryPageLabel
        '
        Me.ListRidsHistoryPageLabel.AutoSize = True
        Me.ListRidsHistoryPageLabel.Location = New System.Drawing.Point(375, 9)
        Me.ListRidsHistoryPageLabel.Name = "ListRidsHistoryPageLabel"
        Me.ListRidsHistoryPageLabel.Size = New System.Drawing.Size(35, 13)
        Me.ListRidsHistoryPageLabel.TabIndex = 18
        Me.ListRidsHistoryPageLabel.Text = "Page:"
        '
        'ListRidsHistoryNextPageButton
        '
        Me.ListRidsHistoryNextPageButton.Enabled = False
        Me.ListRidsHistoryNextPageButton.Location = New System.Drawing.Point(337, 4)
        Me.ListRidsHistoryNextPageButton.Name = "ListRidsHistoryNextPageButton"
        Me.ListRidsHistoryNextPageButton.Size = New System.Drawing.Size(32, 23)
        Me.ListRidsHistoryNextPageButton.TabIndex = 17
        Me.ListRidsHistoryNextPageButton.Text = ">>"
        Me.ListRidsHistoryNextPageButton.UseVisualStyleBackColor = True
        '
        'ListRidsHistoryPreviousPageButton
        '
        Me.ListRidsHistoryPreviousPageButton.Location = New System.Drawing.Point(299, 4)
        Me.ListRidsHistoryPreviousPageButton.Name = "ListRidsHistoryPreviousPageButton"
        Me.ListRidsHistoryPreviousPageButton.Size = New System.Drawing.Size(32, 23)
        Me.ListRidsHistoryPreviousPageButton.TabIndex = 15
        Me.ListRidsHistoryPreviousPageButton.Text = "<<"
        Me.ListRidsHistoryPreviousPageButton.UseVisualStyleBackColor = True
        '
        'TabPage8
        '
        Me.TabPage8.Controls.Add(Me.PutVisionRidLabel)
        Me.TabPage8.Controls.Add(Me.PutVisionRidTextBox)
        Me.TabPage8.Controls.Add(Me.PutVisionButton)
        Me.TabPage8.Controls.Add(Me.PutVisionTextBox)
        Me.TabPage8.Controls.Add(Me.PutVisionLabel)
        Me.TabPage8.Location = New System.Drawing.Point(4, 22)
        Me.TabPage8.Name = "TabPage8"
        Me.TabPage8.Size = New System.Drawing.Size(779, 446)
        Me.TabPage8.TabIndex = 3
        Me.TabPage8.Text = "Vision"
        Me.TabPage8.UseVisualStyleBackColor = True
        '
        'PutVisionRidLabel
        '
        Me.PutVisionRidLabel.AutoSize = True
        Me.PutVisionRidLabel.Location = New System.Drawing.Point(26, 18)
        Me.PutVisionRidLabel.Name = "PutVisionRidLabel"
        Me.PutVisionRidLabel.Size = New System.Drawing.Size(26, 13)
        Me.PutVisionRidLabel.TabIndex = 29
        Me.PutVisionRidLabel.Text = "RID"
        '
        'PutVisionRidTextBox
        '
        Me.PutVisionRidTextBox.Location = New System.Drawing.Point(58, 11)
        Me.PutVisionRidTextBox.Name = "PutVisionRidTextBox"
        Me.PutVisionRidTextBox.Size = New System.Drawing.Size(260, 20)
        Me.PutVisionRidTextBox.TabIndex = 27
        '
        'PutVisionButton
        '
        Me.PutVisionButton.Location = New System.Drawing.Point(41, 81)
        Me.PutVisionButton.Name = "PutVisionButton"
        Me.PutVisionButton.Size = New System.Drawing.Size(76, 23)
        Me.PutVisionButton.TabIndex = 25
        Me.PutVisionButton.Text = "Put Vision"
        Me.PutVisionButton.UseVisualStyleBackColor = True
        '
        'PutVisionTextBox
        '
        Me.PutVisionTextBox.Location = New System.Drawing.Point(420, 11)
        Me.PutVisionTextBox.Name = "PutVisionTextBox"
        Me.PutVisionTextBox.Size = New System.Drawing.Size(296, 20)
        Me.PutVisionTextBox.TabIndex = 24
        '
        'PutVisionLabel
        '
        Me.PutVisionLabel.AutoSize = True
        Me.PutVisionLabel.Location = New System.Drawing.Point(391, 18)
        Me.PutVisionLabel.Name = "PutVisionLabel"
        Me.PutVisionLabel.Size = New System.Drawing.Size(23, 13)
        Me.PutVisionLabel.TabIndex = 23
        Me.PutVisionLabel.Text = "Visi"
        '
        'ListRidsHistoryResult
        '
        Me.ListRidsHistoryResult.BackColor = System.Drawing.SystemColors.Window
        Me.ListRidsHistoryResult.CausesValidation = False
        Me.ListRidsHistoryResult.Location = New System.Drawing.Point(1, 32)
        Me.ListRidsHistoryResult.Name = "ListRidsHistoryResult"
        Me.ListRidsHistoryResult.ReadOnly = True
        Me.ListRidsHistoryResult.Size = New System.Drawing.Size(776, 414)
        Me.ListRidsHistoryResult.TabIndex = 12
        Me.ListRidsHistoryResult.Text = ""
        '
        'PatientIdTextBox
        '
        Me.PatientIdTextBox.Location = New System.Drawing.Point(65, 6)
        Me.PatientIdTextBox.Name = "PatientIdTextBox"
        Me.PatientIdTextBox.Size = New System.Drawing.Size(81, 20)
        Me.PatientIdTextBox.TabIndex = 14
        Me.PatientIdTextBox.Text = "84071230581"
        '
        'PatientIdLabel
        '
        Me.PatientIdLabel.AutoSize = True
        Me.PatientIdLabel.Location = New System.Drawing.Point(7, 9)
        Me.PatientIdLabel.Name = "PatientIdLabel"
        Me.PatientIdLabel.Size = New System.Drawing.Size(23, 13)
        Me.PatientIdLabel.TabIndex = 13
        Me.PatientIdLabel.Text = "Visi"
        '
        'PrescriptionTool
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(782, 475)
        Me.Controls.Add(Me.TabControl1)
        Me.MaximizeBox = False
        Me.MaximumSize = New System.Drawing.Size(800, 599)
        Me.Name = "PrescriptionTool"
        Me.Text = "Recip-e Prescription Tool"
        Me.TabControl1.ResumeLayout(False)
        Me.TabPage6.ResumeLayout(False)
        Me.TabPage6.PerformLayout()
        Me.TabPage1.ResumeLayout(False)
        Me.TabPage1.PerformLayout()
        Me.TabPage2.ResumeLayout(False)
        Me.TabPage2.PerformLayout()
        Me.TabPage3.ResumeLayout(False)
        Me.TabPage3.PerformLayout()
        Me.TabPage4.ResumeLayout(False)
        Me.TabPage4.PerformLayout()
        Me.TabPage5.ResumeLayout(False)
        Me.TabPage5.PerformLayout()
        Me.TabPage7.ResumeLayout(False)
        Me.TabPage7.PerformLayout()
        Me.TabPage8.ResumeLayout(False)
        Me.TabPage8.PerformLayout()
        Me.ResumeLayout(False)

    End Sub
    Friend WithEvents TabControl1 As System.Windows.Forms.TabControl
    '
    'TabPage1
    '
    Friend WithEvents TabPage1 As System.Windows.Forms.TabPage
    Friend WithEvents Label2 As System.Windows.Forms.Label
    Friend WithEvents PatientID As System.Windows.Forms.TextBox
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents PrescriptionContent As System.Windows.Forms.RichTextBox
    '
    'TabPage2
    '
    Friend WithEvents TabPage2 As System.Windows.Forms.TabPage
    Friend WithEvents Feedback As System.Windows.Forms.CheckBox
    Friend WithEvents Label3 As System.Windows.Forms.Label
    Friend WithEvents PrescriptionType As System.Windows.Forms.ComboBox
    Friend WithEvents CreatePrescription As System.Windows.Forms.Button
    Friend WithEvents Label4 As System.Windows.Forms.Label
    Friend WithEvents Label5 As System.Windows.Forms.Label
    Friend WithEvents RequestFeedback As System.Windows.Forms.CheckBox
    Friend WithEvents Label6 As System.Windows.Forms.Label
    Friend WithEvents GetPrescriptionContent As System.Windows.Forms.RichTextBox
    Friend WithEvents RevokePrescription As System.Windows.Forms.Button
    Friend WithEvents GetPrescription As System.Windows.Forms.Button
    Friend WithEvents RIDTextBox As System.Windows.Forms.TextBox
    Friend WithEvents GetPrescriptionPatientID As System.Windows.Forms.TextBox
    Friend WithEvents Label8 As System.Windows.Forms.Label
    Friend WithEvents GetPrescriptionDate As System.Windows.Forms.TextBox
    Friend WithEvents Label7 As System.Windows.Forms.Label
    Friend WithEvents GetPrescriptionStatus As Windows.Forms.Button
    '
    'TabPage3
    '
    Friend WithEvents TabPage3 As System.Windows.Forms.TabPage
    Friend WithEvents ListFeedbacksResult As System.Windows.Forms.RichTextBox
    Friend WithEvents ListFeedbacks As System.Windows.Forms.Button
    Friend WithEvents ListFeedbacksUnread As System.Windows.Forms.CheckBox
    '
    'TabPage4
    '
    Friend WithEvents TabPage4 As System.Windows.Forms.TabPage
    Friend WithEvents ListOpenPrescriptionResult As System.Windows.Forms.RichTextBox
    Friend WithEvents ListOpenPrescriptionButton As System.Windows.Forms.Button
    Friend WithEvents Label18 As System.Windows.Forms.Label
    Friend WithEvents ListPrescrPatientId As System.Windows.Forms.TextBox
    '
    'TabPage5
    '
    Friend WithEvents TabPage5 As System.Windows.Forms.TabPage
    Friend WithEvents NotificationPatientID As System.Windows.Forms.TextBox
    Friend WithEvents Label11 As System.Windows.Forms.Label
    Friend WithEvents SendNotification As System.Windows.Forms.Button
    Friend WithEvents Label9 As System.Windows.Forms.Label
    Friend WithEvents txtPrescriptionNotification As System.Windows.Forms.RichTextBox
    Friend WithEvents NotificationExecutorID As System.Windows.Forms.TextBox
    Friend WithEvents Label12 As System.Windows.Forms.Label
    '
    'TabPage6
    '
    Friend WithEvents TabPage6 As System.Windows.Forms.TabPage
    Friend WithEvents LoginButton As System.Windows.Forms.Button
    Friend WithEvents Label13 As System.Windows.Forms.Label
    Friend WithEvents LoginSAMLResponse As System.Windows.Forms.RichTextBox
    Friend WithEvents btnCreateFallbackSession As System.Windows.Forms.Button
    Friend WithEvents SetPrivKeyPwdButton As System.Windows.Forms.Button
    Friend WithEvents Label10 As System.Windows.Forms.Label
    Friend WithEvents txtNotification As System.Windows.Forms.RichTextBox
    '
    'Page7
    '
    Friend WithEvents TabPage7 As System.Windows.Forms.TabPage
    Friend WithEvents PatientIdTextBox As System.Windows.Forms.TextBox
    Friend WithEvents PatientIdLabel As System.Windows.Forms.Label
    Friend WithEvents ListRidsHistoryResult As System.Windows.Forms.RichTextBox
    Friend WithEvents ListRidsHistoryButton As Windows.Forms.Button
    Friend WithEvents ListRidsHistoryTextBox As Windows.Forms.TextBox
    Friend WithEvents ListRidsHistoryPatientIDLabel As Windows.Forms.Label
    Friend WithEvents ListRidsHistoryRichTextBox As Windows.Forms.RichTextBox
    Friend WithEvents ListRidsHistoryPreviousPageButton As Windows.Forms.Button
    Friend WithEvents ListRidsHistoryNextPageButton As Windows.Forms.Button
    Friend WithEvents ListRidsHistoryPageLabel As Windows.Forms.Label
    Friend WithEvents ListOpenRidsPageLabel As Windows.Forms.Label
    Friend WithEvents ExpirtionDateLabel As Windows.Forms.Label
    Friend WithEvents ExpirationDate As Windows.Forms.MonthCalendar
    Friend WithEvents SelectedExpirationDateLabel As Windows.Forms.Label
    Friend WithEvents VisionTextBox As Windows.Forms.TextBox
    Friend WithEvents VisionLabel As Windows.Forms.Label
    '
    'Page8
    '
    Friend WithEvents TabPage8 As System.Windows.Forms.TabPage
    Friend WithEvents PutVisionButton As Windows.Forms.Button
    Friend WithEvents PutVisionTextBox As Windows.Forms.TextBox
    Friend WithEvents PutVisionLabel As Windows.Forms.Label
    Friend WithEvents PutVisionRidLabel As Windows.Forms.Label
    Friend WithEvents PutVisionRidTextBox As Windows.Forms.TextBox
    Friend WithEvents ListOpenPrescriptionsPreviousButton As Windows.Forms.Button
    Friend WithEvents ListOpenPrescriptionsNextButton As Windows.Forms.Button
    Friend WithEvents ListRidsHistoryCheckBox As Windows.Forms.CheckBox
    Friend WithEvents BulkAmountTextBox As Windows.Forms.TextBox
    Friend WithEvents BulkAmountLabel As Windows.Forms.Label
    Friend WithEvents BulkLabel As Windows.Forms.Label
    Friend WithEvents BulkCheckBox As Windows.Forms.CheckBox

End Class
