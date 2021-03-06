package de.vogella.rcp.intro.wizards.wizard;

import org.eclipse.jface.wizard.Wizard;

public class MyWizard extends Wizard {

  protected MyPageOne one;
  protected MyPageTwo two;
  protected MyPageThree three;



  public MyWizard() {
    super();
    setNeedsProgressMonitor(true);
  }

  @Override
  public String getWindowTitle() {
    return "Export My Data";
  }

  @Override
  public void addPages() {
    one = new MyPageOne();
    two = new MyPageTwo();
    three= new MyPageThree();

    addPage(one);
    addPage(two);
    addPage(three);
  }
  
  @Override
  public boolean performFinish() {
    // Print the result to the console
    System.out.println(one.getText1());
    System.out.println(two.getText1());
    System.out.println(three.getText1());

    return true;
  }

}
 
