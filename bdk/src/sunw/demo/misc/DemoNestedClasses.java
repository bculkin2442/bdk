package sunw.demo.misc;

import java.awt.*;
import java.awt.event.*;

import sunw.demo.juggler.*;

public class DemoNestedClasses extends Frame
{

    /**
     *
     */
    private static final long serialVersionUID = 158892239731348630L;
    Button startButton = new Button("Start Juggling");
    Button stopButton = new Button("Stop Juggling");
    Juggler juggler = new Juggler();

    DemoNestedClasses()
    {
        super("Demo Nested Class");
        setLayout(new FlowLayout());
        add(startButton);
        add(stopButton);
        add(juggler);

        startButton.addActionListener(new StartButtonListener());
        stopButton.addActionListener(new StopButtonListener());
    }

    class StartButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            juggler.startJuggling(e);
        }
    }

    class StopButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            juggler.stopJuggling(e);
        }
    }

    public static void main(String args[]) {
        Frame f = new DemoNestedClasses();
        f.pack();
        f.show();
    }
}
