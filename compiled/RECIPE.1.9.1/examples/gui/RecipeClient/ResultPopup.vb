Imports System.Drawing
Imports java.util

Public Class ResultPopup
    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)
        Me.DialogResult = System.Windows.Forms.DialogResult.OK
    End Sub

    Private Sub ResultPopup_Load(sender As Object, e As EventArgs) Handles MyBase.Load

    End Sub

    Friend Sub initResults(items As List)
        Dim TextBoxArray() As System.Windows.Forms.TextBox = {Me.CreatedRid1, Me.CreatedRid2, Me.CreatedRid3, Me.CreatedRid4, Me.CreatedRid5, Me.CreatedRid6, Me.CreatedRid7, Me.CreatedRid8, Me.CreatedRid9, Me.CreatedRid10, Me.CreatedRid11, Me.CreatedRid12, Me.CreatedRid13, Me.CreatedRid14, Me.CreatedRid15, Me.CreatedRid16, Me.CreatedRid17, Me.CreatedRid18, Me.CreatedRid19, Me.CreatedRid20, Me.CreatedRid21, Me.CreatedRid22, Me.CreatedRid23, Me.CreatedRid24, Me.CreatedRid25, Me.CreatedRid26, Me.CreatedRid27, Me.CreatedRid28, Me.CreatedRid29, Me.CreatedRid30}
        Dim LabelArray() As System.Windows.Forms.Label = {Me.Label1, Me.Label2, Me.Label3, Me.Label4, Me.Label5, Me.Label6, Me.Label7, Me.Label8, Me.Label9, Me.Label10, Me.Label11, Me.Label12, Me.Label13, Me.Label14, Me.Label15, Me.Label16, Me.Label17, Me.Label18, Me.Label19, Me.Label20, Me.Label21, Me.Label22, Me.Label23, Me.Label24, Me.Label25, Me.Label26, Me.Label27, Me.Label28, Me.Label29, Me.Label30}
        ''Me.ClientSize = New System.Drawing.Size(367, 100 + 50 * items.size)
        ''Me.Button1.Location = New System.Drawing.Point(169, 70 + 50 * items.size)
        Dim errorCount = 0
        Dim results As String = ""
        For i = 0 To items.size - 1
            Dim item As be.business.connector.recipe.prescriber.dto.CreatePrescriptionDTO = items.get(i)
            TextBoxArray(i).Text = item.getRid
            TextBoxArray(i).Visible = True
            TextBoxArray(i).Size = New System.Drawing.Size(135, 20)
            LabelArray(i).Visible = True
            If item.isErrorOccured Then
                TextBoxArray(i).Text = item.getException.getMessage
                TextBoxArray(i).ForeColor = Color.Red
                TextBoxArray(i).Size = New System.Drawing.Size(262, 20)
                LabelArray(i).ForeColor = Color.Red

            End If
        Next
    End Sub
End Class