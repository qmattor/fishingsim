import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class frame extends JFrame{
	QMpanel p;
	public frame() throws IOException
	{
		JFrame fishing = new JFrame();
		fishing.setTitle("Fishing simulator");
		fishing.setResizable(false);
		fishing.setSize(1000,1000);
		fishing.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = fishing.getContentPane();
		p = new QMpanel();
		pane.add(p);
		fishing.setVisible(true);
		p.requestFocus();

	}
}
