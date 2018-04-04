package be.ehealth.technicalconnector.service.sts.security.impl.beid.impl;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.impl.beid.PinPadPanel;
import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class PinPadPanelImpl extends JPanel implements PinPadPanel {
   private static final long serialVersionUID = 1926412319764038223L;
   private static final int MIN_PIN_SIZE = 4;
   private static final int MAX_PIN_SIZE = 12;
   private JTextField txtPassWord;
   private JLabel lblRetriesleft;
   private JButton btnGo;
   private ActionListener actionListenerGoButton;

   public PinPadPanelImpl() {
      this.setLayout((LayoutManager)null);
      Toolkit toolkit = this.getToolkit();
      toolkit.addAWTEventListener(new PinPadPanelImpl.KeyBoardAWTEventListener(null), 8L);
      Font fntButton = new Font("Arial", 1, 35);
      Font fntLabel = new Font("Arial", 1, 12);
      Font fntField = new Font("Arial", 1, 35);
      Color clrGreen = new Color(128, 175, 60);
      Color clrRed = Color.red;
      Insets marginButton = new Insets(0, 0, 0, 0);
      this.add(this.createButton("1", fntButton, marginButton, new Rectangle(22, 126, 75, 50)));
      this.add(this.createButton("2", fntButton, marginButton, new Rectangle(104, 126, 75, 50)));
      this.add(this.createButton("3", fntButton, marginButton, new Rectangle(192, 126, 75, 50)));
      this.add(this.createButton("4", fntButton, marginButton, new Rectangle(22, 187, 75, 50)));
      this.add(this.createButton("5", fntButton, marginButton, new Rectangle(104, 187, 75, 50)));
      this.add(this.createButton("6", fntButton, marginButton, new Rectangle(192, 187, 75, 50)));
      this.add(this.createButton("7", fntButton, marginButton, new Rectangle(22, 250, 75, 50)));
      this.add(this.createButton("8", fntButton, marginButton, new Rectangle(104, 250, 75, 50)));
      this.add(this.createButton("9", fntButton, marginButton, new Rectangle(192, 250, 75, 50)));
      this.add(this.createButton("0", fntButton, marginButton, new Rectangle(22, 311, 75, 50)));
      this.add(this.createButton("<", fntButton, marginButton, new Rectangle(104, 311, 75, 50), new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            PinPadPanelImpl.this.processBackspace();
         }
      }));
      this.btnGo = new JButton("GO");
      this.btnGo.setForeground(clrGreen);
      this.btnGo.setMargin(marginButton);
      this.btnGo.setFont(fntButton);
      this.btnGo.setBounds(192, 311, 75, 50);
      this.btnGo.setEnabled(false);
      this.add(this.btnGo);
      this.txtPassWord = new JPasswordField();
      this.txtPassWord.setHorizontalAlignment(0);
      this.txtPassWord.setFocusable(false);
      this.txtPassWord.setFont(fntField);
      this.txtPassWord.setText("");
      this.txtPassWord.setBorder(new LineBorder(Color.GRAY, 1, true));
      this.txtPassWord.setBounds(22, 65, 245, 50);
      this.add(this.txtPassWord);
      JLabel lblPinCode = new JLabel("Please enter your pincode to authenticate yourself.");
      lblPinCode.setFont(fntLabel);
      lblPinCode.setBounds(22, 40, 245, 14);
      this.add(lblPinCode);
      this.setFocusable(true);
      this.lblRetriesleft = new JLabel("Retries left: ");
      this.lblRetriesleft.setFont(fntLabel);
      this.lblRetriesleft.setForeground(clrRed);
      this.lblRetriesleft.setBounds(22, 15, 245, 14);
      this.lblRetriesleft.setVisible(false);
      this.add(this.lblRetriesleft);
   }

   private JButton createButton(String label, Font fntButton, Insets marginButton, Rectangle bounds) {
      return this.createButton(label, fntButton, marginButton, bounds, new PinPadPanelImpl.NumberActionListener(label));
   }

   private JButton createButton(String label, Font fntButton, Insets marginButton, Rectangle bounds, ActionListener listener) {
      JButton btnNumberOne = new JButton(label);
      btnNumberOne.setMargin(marginButton);
      btnNumberOne.setFont(fntButton);
      btnNumberOne.setBounds(bounds);
      btnNumberOne.addActionListener(listener);
      return btnNumberOne;
   }

   private void processBackspace() {
      if (this.txtPassWord.getText() != null && this.txtPassWord.getText().length() > 0) {
         this.txtPassWord.setText(this.txtPassWord.getText().substring(0, this.txtPassWord.getText().length() - 1));
         this.validateGoButton();
      }

   }

   public void setActionListenerOnGoButton(ActionListener action) {
      this.actionListenerGoButton = action;
      this.btnGo.addActionListener(action);
   }

   private void processContent(String content) {
      if (content.matches("[0-9]")) {
         this.txtPassWord.setText(this.txtPassWord.getText() + content);
         this.validateGoButton();
      }

   }

   private void validateGoButton() {
      int pinSize = this.txtPassWord.getText().length();
      if (4 <= pinSize && pinSize <= 12) {
         this.btnGo.setEnabled(true);
      } else {
         this.btnGo.setEnabled(false);
      }

   }

   public char[] getPassWord() throws TechnicalConnectorException {
      if (!this.btnGo.isEnabled()) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.CORE_TECHNICAL, new Object[]{"no pin code provided"});
      } else {
         return this.txtPassWord.getText().toCharArray();
      }
   }

   public void setRetriesLeft(int retriesLeft) {
      if (retriesLeft != -1) {
         this.lblRetriesleft.setText(this.lblRetriesleft.getText() + retriesLeft);
         this.lblRetriesleft.setVisible(true);
      }

   }

   private class KeyBoardAWTEventListener implements AWTEventListener {
      private KeyBoardAWTEventListener() {
      }

      public void eventDispatched(AWTEvent event) {
         if (event instanceof KeyEvent) {
            KeyEvent key = (KeyEvent)event;
            if (key.getID() == 401) {
               if (key.getKeyCode() == 8) {
                  PinPadPanelImpl.this.processBackspace();
               } else if (key.getKeyCode() == 10) {
                  PinPadPanelImpl.this.validateGoButton();
                  if (PinPadPanelImpl.this.btnGo.isEnabled()) {
                     ActionEvent action = new ActionEvent(PinPadPanelImpl.this.btnGo, 1001, PinPadPanelImpl.this.btnGo.getActionCommand(), System.currentTimeMillis(), 16);
                     PinPadPanelImpl.this.actionListenerGoButton.actionPerformed(action);
                  }
               } else {
                  PinPadPanelImpl.this.processContent(Character.toString(key.getKeyChar()));
               }
            }
         }

      }

      // $FF: synthetic method
      KeyBoardAWTEventListener(Object x1) {
         this();
      }
   }

   private class NumberActionListener implements ActionListener {
      private String content;

      public NumberActionListener(String content) {
         this.content = content;
      }

      public void actionPerformed(ActionEvent e) {
         PinPadPanelImpl.this.processContent(this.content);
      }
   }
}
