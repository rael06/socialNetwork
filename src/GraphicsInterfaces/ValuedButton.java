package GraphicsInterfaces;

import javax.swing.*;

public class ValuedButton extends JButton {
    private Object value;

    public Object getValue() {
        return value;
    }

    public ValuedButton(String buttonTitle, Object _value) {
        super(buttonTitle);
        value = _value;
    }

}
