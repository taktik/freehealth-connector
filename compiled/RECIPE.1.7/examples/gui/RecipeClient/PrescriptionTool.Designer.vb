<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class PrescriptionTool
    Inherits System.Windows.Forms.Form

    'Form remplace la méthode Dispose pour nettoyer la liste des composants.
    <System.Diagnostics.DebuggerNonUserCode()> _
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
    <System.Diagnostics.DebuggerStepThrough()> _
    Private Sub InitializeComponent()
        Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(PrescriptionTool))
        Me.TabControl1 = New System.Windows.Forms.TabControl()
        Me.TabPage6 = New System.Windows.Forms.TabPage()
        Me.btnCreateFallbackSession = New System.Windows.Forms.Button()
        Me.LoginButton = New System.Windows.Forms.Button()
        Me.Label13 = New System.Windows.Forms.Label()
        Me.LoginSAMLResponse = New System.Windows.Forms.RichTextBox()
        Me.TabPage1 = New System.Windows.Forms.TabPage()
        Me.Label4 = New System.Windows.Forms.Label()
        Me.CreatePrescription = New System.Windows.Forms.Button()
        Me.PrescriptionType = New System.Windows.Forms.ComboBox()
        Me.Label3 = New System.Windows.Forms.Label()
        Me.Feedback = New System.Windows.Forms.CheckBox()
        Me.Label2 = New System.Windows.Forms.Label()
        Me.PatientID = New System.Windows.Forms.TextBox()
        Me.Label1 = New System.Windows.Forms.Label()
        Me.PrescriptionContent = New System.Windows.Forms.RichTextBox()
        Me.TabPage2 = New System.Windows.Forms.TabPage()
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
        Me.tbpConfiguration = New System.Windows.Forms.TabPage()
        Me.grpOldSystemKeystore = New System.Windows.Forms.GroupBox()
        Me.btnOldSystemkeystoreSave = New System.Windows.Forms.Button()
        Me.txtOldSystemkeystoreKboRiziv = New System.Windows.Forms.TextBox()
        Me.Label14 = New System.Windows.Forms.Label()
        Me.txtOldSystemkeystoreDir = New System.Windows.Forms.TextBox()
        Me.Label15 = New System.Windows.Forms.Label()
        Me.txtOldSystemKeystorePass = New System.Windows.Forms.TextBox()
        Me.Label16 = New System.Windows.Forms.Label()
        Me.txtOldSystemKeystoreFile = New System.Windows.Forms.TextBox()
        Me.Label17 = New System.Windows.Forms.Label()
        Me.grpSystemCerticate = New System.Windows.Forms.GroupBox()
        Me.btnSystemKeystoreSave = New System.Windows.Forms.Button()
        Me.txtSystemkeystoreKboRiziv = New System.Windows.Forms.TextBox()
        Me.lblSystemkeystoreCBERiziv = New System.Windows.Forms.Label()
        Me.txtSystemkeystoreDir = New System.Windows.Forms.TextBox()
        Me.lblSystemKeystorePass = New System.Windows.Forms.Label()
        Me.txtSystemKeystorePass = New System.Windows.Forms.TextBox()
        Me.lblSystemkeystoreDir = New System.Windows.Forms.Label()
        Me.txtSystemKeystoreFile = New System.Windows.Forms.TextBox()
        Me.lblSystemKeystore = New System.Windows.Forms.Label()
        Me.TabControl1.SuspendLayout()
        Me.TabPage6.SuspendLayout()
        Me.TabPage1.SuspendLayout()
        Me.TabPage2.SuspendLayout()
        Me.TabPage3.SuspendLayout()
        Me.TabPage4.SuspendLayout()
        Me.TabPage5.SuspendLayout()
        Me.tbpConfiguration.SuspendLayout()
        Me.grpOldSystemKeystore.SuspendLayout()
        Me.grpSystemCerticate.SuspendLayout()
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
        Me.TabControl1.Controls.Add(Me.tbpConfiguration)
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
        Me.TabPage1.Controls.Add(Me.Label4)
        Me.TabPage1.Controls.Add(Me.CreatePrescription)
        Me.TabPage1.Controls.Add(Me.PrescriptionType)
        Me.TabPage1.Controls.Add(Me.Label3)
        Me.TabPage1.Controls.Add(Me.Feedback)
        Me.TabPage1.Controls.Add(Me.Label2)
        Me.TabPage1.Controls.Add(Me.PatientID)
        Me.TabPage1.Controls.Add(Me.Label1)
        Me.TabPage1.Controls.Add(Me.PrescriptionContent)
        Me.TabPage1.Location = New System.Drawing.Point(4, 22)
        Me.TabPage1.Name = "TabPage1"
        Me.TabPage1.Padding = New System.Windows.Forms.Padding(3)
        Me.TabPage1.Size = New System.Drawing.Size(779, 446)
        Me.TabPage1.TabIndex = 0
        Me.TabPage1.Text = "Create Prescription"
        Me.TabPage1.UseVisualStyleBackColor = True
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
        Me.PrescriptionType.DisplayMember = "P0,P1,P2"
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
        Me.TabPage4.Controls.Add(Me.ListPrescrPatientId)
        Me.TabPage4.Controls.Add(Me.Label18)
        Me.TabPage4.Controls.Add(Me.ListOpenPrescriptionResult)
        Me.TabPage4.Controls.Add(Me.ListOpenPrescriptionButton)
        Me.TabPage4.Location = New System.Drawing.Point(4, 22)
        Me.TabPage4.Name = "TabPage4"
        Me.TabPage4.Size = New System.Drawing.Size(779, 446)
        Me.TabPage4.TabIndex = 3
        Me.TabPage4.Text = "List Open Prescriptions"
        Me.TabPage4.UseVisualStyleBackColor = True
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
        Me.ListOpenPrescriptionButton.Text = "List Open Prescriptions"
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
        'tbpConfiguration
        '
        Me.tbpConfiguration.Controls.Add(Me.grpOldSystemKeystore)
        Me.tbpConfiguration.Controls.Add(Me.grpSystemCerticate)
        Me.tbpConfiguration.Location = New System.Drawing.Point(4, 22)
        Me.tbpConfiguration.Name = "tbpConfiguration"
        Me.tbpConfiguration.Size = New System.Drawing.Size(779, 446)
        Me.tbpConfiguration.TabIndex = 6
        Me.tbpConfiguration.Tag = ""
        Me.tbpConfiguration.Text = "Configuration"
        Me.tbpConfiguration.UseVisualStyleBackColor = True
        '
        'grpOldSystemKeystore
        '
        Me.grpOldSystemKeystore.Controls.Add(Me.btnOldSystemkeystoreSave)
        Me.grpOldSystemKeystore.Controls.Add(Me.txtOldSystemkeystoreKboRiziv)
        Me.grpOldSystemKeystore.Controls.Add(Me.Label14)
        Me.grpOldSystemKeystore.Controls.Add(Me.txtOldSystemkeystoreDir)
        Me.grpOldSystemKeystore.Controls.Add(Me.Label15)
        Me.grpOldSystemKeystore.Controls.Add(Me.txtOldSystemKeystorePass)
        Me.grpOldSystemKeystore.Controls.Add(Me.Label16)
        Me.grpOldSystemKeystore.Controls.Add(Me.txtOldSystemKeystoreFile)
        Me.grpOldSystemKeystore.Controls.Add(Me.Label17)
        Me.grpOldSystemKeystore.Location = New System.Drawing.Point(30, 230)
        Me.grpOldSystemKeystore.Name = "grpOldSystemKeystore"
        Me.grpOldSystemKeystore.Size = New System.Drawing.Size(604, 186)
        Me.grpOldSystemKeystore.TabIndex = 2
        Me.grpOldSystemKeystore.TabStop = False
        Me.grpOldSystemKeystore.Text = "Previous system certificate"
        '
        'btnOldSystemkeystoreSave
        '
        Me.btnOldSystemkeystoreSave.Location = New System.Drawing.Point(483, 144)
        Me.btnOldSystemkeystoreSave.Name = "btnOldSystemkeystoreSave"
        Me.btnOldSystemkeystoreSave.Size = New System.Drawing.Size(75, 23)
        Me.btnOldSystemkeystoreSave.TabIndex = 9
        Me.btnOldSystemkeystoreSave.Text = "Save"
        Me.btnOldSystemkeystoreSave.UseVisualStyleBackColor = True
        '
        'txtOldSystemkeystoreKboRiziv
        '
        Me.txtOldSystemkeystoreKboRiziv.Location = New System.Drawing.Point(292, 118)
        Me.txtOldSystemkeystoreKboRiziv.Name = "txtOldSystemkeystoreKboRiziv"
        Me.txtOldSystemkeystoreKboRiziv.Size = New System.Drawing.Size(266, 20)
        Me.txtOldSystemkeystoreKboRiziv.TabIndex = 7
        '
        'Label14
        '
        Me.Label14.AutoSize = True
        Me.Label14.Location = New System.Drawing.Point(15, 118)
        Me.Label14.Name = "Label14"
        Me.Label14.Size = New System.Drawing.Size(240, 13)
        Me.Label14.TabIndex = 6
        Me.Label14.Text = "CBE physician (PROP_KEYSTORE_RIZIV_KBO)"
        '
        'txtOldSystemkeystoreDir
        '
        Me.txtOldSystemkeystoreDir.Location = New System.Drawing.Point(292, 88)
        Me.txtOldSystemkeystoreDir.Name = "txtOldSystemkeystoreDir"
        Me.txtOldSystemkeystoreDir.Size = New System.Drawing.Size(266, 20)
        Me.txtOldSystemkeystoreDir.TabIndex = 5
        '
        'Label15
        '
        Me.Label15.AutoSize = True
        Me.Label15.Location = New System.Drawing.Point(15, 62)
        Me.Label15.Name = "Label15"
        Me.Label15.Size = New System.Drawing.Size(232, 13)
        Me.Label15.TabIndex = 4
        Me.Label15.Text = "Keystore password (KEYSTORE_PASSWORD)"
        '
        'txtOldSystemKeystorePass
        '
        Me.txtOldSystemKeystorePass.Location = New System.Drawing.Point(292, 62)
        Me.txtOldSystemKeystorePass.Name = "txtOldSystemKeystorePass"
        Me.txtOldSystemKeystorePass.PasswordChar = Global.Microsoft.VisualBasic.ChrW(42)
        Me.txtOldSystemKeystorePass.Size = New System.Drawing.Size(266, 20)
        Me.txtOldSystemKeystorePass.TabIndex = 3
        Me.txtOldSystemKeystorePass.Text = "Password1"
        '
        'Label16
        '
        Me.Label16.AutoSize = True
        Me.Label16.Location = New System.Drawing.Point(15, 91)
        Me.Label16.Name = "Label16"
        Me.Label16.Size = New System.Drawing.Size(268, 13)
        Me.Label16.TabIndex = 2
        Me.Label16.Text = "Keystore directory (KEYSTORE_AUTH_P12_FOLDER)"
        '
        'txtOldSystemKeystoreFile
        '
        Me.txtOldSystemKeystoreFile.Location = New System.Drawing.Point(292, 36)
        Me.txtOldSystemKeystoreFile.Name = "txtOldSystemKeystoreFile"
        Me.txtOldSystemKeystoreFile.Size = New System.Drawing.Size(266, 20)
        Me.txtOldSystemKeystoreFile.TabIndex = 1
        '
        'Label17
        '
        Me.Label17.AutoSize = True
        Me.Label17.Location = New System.Drawing.Point(15, 36)
        Me.Label17.Name = "Label17"
        Me.Label17.Size = New System.Drawing.Size(183, 13)
        Me.Label17.TabIndex = 0
        Me.Label17.Text = "Keystore file path (KEYSTORE_FILE)"
        '
        'grpSystemCerticate
        '
        Me.grpSystemCerticate.Controls.Add(Me.btnSystemKeystoreSave)
        Me.grpSystemCerticate.Controls.Add(Me.txtSystemkeystoreKboRiziv)
        Me.grpSystemCerticate.Controls.Add(Me.lblSystemkeystoreCBERiziv)
        Me.grpSystemCerticate.Controls.Add(Me.txtSystemkeystoreDir)
        Me.grpSystemCerticate.Controls.Add(Me.lblSystemKeystorePass)
        Me.grpSystemCerticate.Controls.Add(Me.txtSystemKeystorePass)
        Me.grpSystemCerticate.Controls.Add(Me.lblSystemkeystoreDir)
        Me.grpSystemCerticate.Controls.Add(Me.txtSystemKeystoreFile)
        Me.grpSystemCerticate.Controls.Add(Me.lblSystemKeystore)
        Me.grpSystemCerticate.Location = New System.Drawing.Point(30, 23)
        Me.grpSystemCerticate.Name = "grpSystemCerticate"
        Me.grpSystemCerticate.Size = New System.Drawing.Size(604, 182)
        Me.grpSystemCerticate.TabIndex = 1
        Me.grpSystemCerticate.TabStop = False
        Me.grpSystemCerticate.Text = "System certificate"
        '
        'btnSystemKeystoreSave
        '
        Me.btnSystemKeystoreSave.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft
        Me.btnSystemKeystoreSave.Location = New System.Drawing.Point(483, 144)
        Me.btnSystemKeystoreSave.Name = "btnSystemKeystoreSave"
        Me.btnSystemKeystoreSave.Size = New System.Drawing.Size(75, 23)
        Me.btnSystemKeystoreSave.TabIndex = 8
        Me.btnSystemKeystoreSave.Text = "Save"
        Me.btnSystemKeystoreSave.UseVisualStyleBackColor = True
        '
        'txtSystemkeystoreKboRiziv
        '
        Me.txtSystemkeystoreKboRiziv.Location = New System.Drawing.Point(292, 118)
        Me.txtSystemkeystoreKboRiziv.Name = "txtSystemkeystoreKboRiziv"
        Me.txtSystemkeystoreKboRiziv.Size = New System.Drawing.Size(266, 20)
        Me.txtSystemkeystoreKboRiziv.TabIndex = 7
        '
        'lblSystemkeystoreCBERiziv
        '
        Me.lblSystemkeystoreCBERiziv.AutoSize = True
        Me.lblSystemkeystoreCBERiziv.Location = New System.Drawing.Point(15, 118)
        Me.lblSystemkeystoreCBERiziv.Name = "lblSystemkeystoreCBERiziv"
        Me.lblSystemkeystoreCBERiziv.Size = New System.Drawing.Size(240, 13)
        Me.lblSystemkeystoreCBERiziv.TabIndex = 6
        Me.lblSystemkeystoreCBERiziv.Text = "CBE physician (PROP_KEYSTORE_RIZIV_KBO)"
        '
        'txtSystemkeystoreDir
        '
        Me.txtSystemkeystoreDir.Location = New System.Drawing.Point(292, 88)
        Me.txtSystemkeystoreDir.Name = "txtSystemkeystoreDir"
        Me.txtSystemkeystoreDir.Size = New System.Drawing.Size(266, 20)
        Me.txtSystemkeystoreDir.TabIndex = 5
        '
        'lblSystemKeystorePass
        '
        Me.lblSystemKeystorePass.AutoSize = True
        Me.lblSystemKeystorePass.Location = New System.Drawing.Point(15, 62)
        Me.lblSystemKeystorePass.Name = "lblSystemKeystorePass"
        Me.lblSystemKeystorePass.Size = New System.Drawing.Size(232, 13)
        Me.lblSystemKeystorePass.TabIndex = 4
        Me.lblSystemKeystorePass.Text = "Keystore password (KEYSTORE_PASSWORD)"
        '
        'txtSystemKeystorePass
        '
        Me.txtSystemKeystorePass.Location = New System.Drawing.Point(292, 62)
        Me.txtSystemKeystorePass.Name = "txtSystemKeystorePass"
        Me.txtSystemKeystorePass.PasswordChar = Global.Microsoft.VisualBasic.ChrW(42)
        Me.txtSystemKeystorePass.Size = New System.Drawing.Size(266, 20)
        Me.txtSystemKeystorePass.TabIndex = 3
        Me.txtSystemKeystorePass.Text = "Password1"
        '
        'lblSystemkeystoreDir
        '
        Me.lblSystemkeystoreDir.AutoSize = True
        Me.lblSystemkeystoreDir.Location = New System.Drawing.Point(15, 91)
        Me.lblSystemkeystoreDir.Name = "lblSystemkeystoreDir"
        Me.lblSystemkeystoreDir.Size = New System.Drawing.Size(268, 13)
        Me.lblSystemkeystoreDir.TabIndex = 2
        Me.lblSystemkeystoreDir.Text = "Keystore directory (KEYSTORE_AUTH_P12_FOLDER)"
        '
        'txtSystemKeystoreFile
        '
        Me.txtSystemKeystoreFile.Location = New System.Drawing.Point(292, 36)
        Me.txtSystemKeystoreFile.Name = "txtSystemKeystoreFile"
        Me.txtSystemKeystoreFile.Size = New System.Drawing.Size(266, 20)
        Me.txtSystemKeystoreFile.TabIndex = 1
        '
        'lblSystemKeystore
        '
        Me.lblSystemKeystore.AutoSize = True
        Me.lblSystemKeystore.Location = New System.Drawing.Point(15, 36)
        Me.lblSystemKeystore.Name = "lblSystemKeystore"
        Me.lblSystemKeystore.Size = New System.Drawing.Size(183, 13)
        Me.lblSystemKeystore.TabIndex = 0
        Me.lblSystemKeystore.Text = "Keystore file path (KEYSTORE_FILE)"
        '
        'PrescriptionTool
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(784, 475)
        Me.Controls.Add(Me.TabControl1)
        Me.MaximizeBox = False
        Me.MaximumSize = New System.Drawing.Size(800, 600)
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
        Me.tbpConfiguration.ResumeLayout(False)
        Me.grpOldSystemKeystore.ResumeLayout(False)
        Me.grpOldSystemKeystore.PerformLayout()
        Me.grpSystemCerticate.ResumeLayout(False)
        Me.grpSystemCerticate.PerformLayout()
        Me.ResumeLayout(False)

    End Sub
    Friend WithEvents TabControl1 As System.Windows.Forms.TabControl
    Friend WithEvents TabPage1 As System.Windows.Forms.TabPage
    Friend WithEvents Label2 As System.Windows.Forms.Label
    Friend WithEvents PatientID As System.Windows.Forms.TextBox
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents PrescriptionContent As System.Windows.Forms.RichTextBox
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
    Friend WithEvents TabPage3 As System.Windows.Forms.TabPage
    Friend WithEvents ListFeedbacksResult As System.Windows.Forms.RichTextBox
    Friend WithEvents ListFeedbacks As System.Windows.Forms.Button
    Friend WithEvents ListFeedbacksUnread As System.Windows.Forms.CheckBox
    Friend WithEvents TabPage4 As System.Windows.Forms.TabPage
    Friend WithEvents ListOpenPrescriptionResult As System.Windows.Forms.RichTextBox
    Friend WithEvents ListOpenPrescriptionButton As System.Windows.Forms.Button
    Friend WithEvents TabPage5 As System.Windows.Forms.TabPage
    Friend WithEvents NotificationPatientID As System.Windows.Forms.TextBox
    Friend WithEvents Label11 As System.Windows.Forms.Label
    Friend WithEvents SendNotification As System.Windows.Forms.Button
    Friend WithEvents Label9 As System.Windows.Forms.Label
    Friend WithEvents txtPrescriptionNotification As System.Windows.Forms.RichTextBox
    Friend WithEvents NotificationExecutorID As System.Windows.Forms.TextBox
    Friend WithEvents Label12 As System.Windows.Forms.Label
    Friend WithEvents TabPage6 As System.Windows.Forms.TabPage
    Friend WithEvents LoginButton As System.Windows.Forms.Button
    Friend WithEvents Label13 As System.Windows.Forms.Label
    Friend WithEvents LoginSAMLResponse As System.Windows.Forms.RichTextBox
    Friend WithEvents btnCreateFallbackSession As System.Windows.Forms.Button
    Friend WithEvents SetPrivKeyPwdButton As System.Windows.Forms.Button
    Friend WithEvents Label10 As System.Windows.Forms.Label
    Friend WithEvents txtNotification As System.Windows.Forms.RichTextBox
    Friend WithEvents tbpConfiguration As System.Windows.Forms.TabPage
    Friend WithEvents grpSystemCerticate As System.Windows.Forms.GroupBox
    Friend WithEvents txtSystemkeystoreKboRiziv As System.Windows.Forms.TextBox
    Friend WithEvents lblSystemkeystoreCBERiziv As System.Windows.Forms.Label
    Friend WithEvents txtSystemkeystoreDir As System.Windows.Forms.TextBox
    Friend WithEvents lblSystemKeystorePass As System.Windows.Forms.Label
    Friend WithEvents txtSystemKeystorePass As System.Windows.Forms.TextBox
    Friend WithEvents lblSystemkeystoreDir As System.Windows.Forms.Label
    Friend WithEvents txtSystemKeystoreFile As System.Windows.Forms.TextBox
    Friend WithEvents lblSystemKeystore As System.Windows.Forms.Label
    Friend WithEvents grpOldSystemKeystore As System.Windows.Forms.GroupBox
    Friend WithEvents txtOldSystemkeystoreKboRiziv As System.Windows.Forms.TextBox
    Friend WithEvents Label14 As System.Windows.Forms.Label
    Friend WithEvents txtOldSystemkeystoreDir As System.Windows.Forms.TextBox
    Friend WithEvents Label15 As System.Windows.Forms.Label
    Friend WithEvents txtOldSystemKeystorePass As System.Windows.Forms.TextBox
    Friend WithEvents Label16 As System.Windows.Forms.Label
    Friend WithEvents txtOldSystemKeystoreFile As System.Windows.Forms.TextBox
    Friend WithEvents Label17 As System.Windows.Forms.Label
    Friend WithEvents btnOldSystemkeystoreSave As System.Windows.Forms.Button
    Friend WithEvents btnSystemKeystoreSave As System.Windows.Forms.Button
    Friend WithEvents ListPrescrPatientId As System.Windows.Forms.TextBox
    Friend WithEvents Label18 As System.Windows.Forms.Label

End Class
