package com.javacodegeeks.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    private JTextField results;
    private JButton clearBtn;
    private JButton signBtn;
    private JButton percentBtn;
    private JButton divideBtn;
    private JButton sevenBtn;
    private JButton eightBtn;
    private JButton nineBtn;
    private JButton multiplyBtn;
    private JButton fourBtn;
    private JButton fiveBtn;
    private JButton sixBtn;
    private JButton minusBtn;
    private JButton oneBtn;
    private JButton twoBtn;
    private JButton threeBtn;
    private JButton addBtn;
    private JButton zeroBtn;
    private JButton digitBtn;
    private JButton equalBtn;
    private JPanel calculatorView;
    private Double leftOperand;
    private Double rightOperand;
    private Operation calcOperation;

    public Calculator() {

        sevenBtn.addActionListener(new NumberBtnClicked(sevenBtn.getText()));
        eightBtn.addActionListener(new NumberBtnClicked(eightBtn.getText()));
        nineBtn.addActionListener(new NumberBtnClicked(nineBtn.getText()));
        fourBtn.addActionListener(new NumberBtnClicked(fourBtn.getText()));
        fiveBtn.addActionListener(new NumberBtnClicked(fiveBtn.getText()));
        sixBtn.addActionListener(new NumberBtnClicked(sixBtn.getText()));
        oneBtn.addActionListener(new NumberBtnClicked(oneBtn.getText()));
        twoBtn.addActionListener(new NumberBtnClicked(twoBtn.getText()));
        threeBtn.addActionListener(new NumberBtnClicked(threeBtn.getText()));
        zeroBtn.addActionListener(new NumberBtnClicked(zeroBtn.getText()));

        percentBtn.addActionListener(new OperationBtnClicked(Operation.PERCENTAGE));
        multiplyBtn.addActionListener(new OperationBtnClicked(Operation.MULTIPLICATION));
        divideBtn.addActionListener(new OperationBtnClicked(Operation.DIVISION));
        minusBtn.addActionListener(new OperationBtnClicked(Operation.SUBTRACTION));
        addBtn.addActionListener(new OperationBtnClicked(Operation.ADDITION));
        equalBtn.addActionListener(new EqualBtnClicked());
        clearBtn.addActionListener(new ClearBtnClicked());
        signBtn.addActionListener(new SignBtnClicked());
        digitBtn.addActionListener(new DigitBtnClicked());
    }

    private class NumberBtnClicked implements ActionListener {

        private String value;

        public NumberBtnClicked(String value) {
            this.value = value;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(leftOperand == null || leftOperand == 0.0) {
                value = results.getText() + value;
            }else{
                rightOperand = Double.valueOf(value);
            }
            results.setText(value);

        }
    }

    private class OperationBtnClicked implements ActionListener {

        private Operation operation;

        public OperationBtnClicked(Operation operation) {
            this.operation = operation;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            calcOperation = operation;
            leftOperand = Double.valueOf(results.getText());
        }
    }

    private class ClearBtnClicked implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            results.setText("");
            leftOperand = 0.0;
            rightOperand = 0.0;
        }
    }

    private class DigitBtnClicked implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            results.setText(results.getText() + ".");

        }
    }

    private class EqualBtnClicked implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Double output = calcOperation.getOperator().applyAsDouble(leftOperand, rightOperand);
            results.setText(output%1==0?String.valueOf(output.intValue()):String.valueOf(output));
            leftOperand = 0.0;
            rightOperand = 0.0;
        }
    }

    private class SignBtnClicked implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            results.setText("-"+ results.getText());
        }
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new Calculator().calculatorView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
