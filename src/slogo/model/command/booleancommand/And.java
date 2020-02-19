package slogo.model.command.booleancommand;

public class And extends BooleanCommand {

  /**
   * constructor of and logic
   * @param bool1 condition 1
   * @param bool2 condition 2
   */
  public And(boolean bool1, boolean bool2){
   super();
   super.changeBooleanResultToDouble(bool1&&bool2);
  }

  public static void main(String[] args) {
    boolean a = false;
    boolean b = true;
    And c = new And(a,b);
    System.out.println(a+" && " + b + " = "+c.getResult());

    a = true;
    b = false;
    c = new And(a,b);
    System.out.println(a+" && " + b + " = "+c.getResult());

    a = false;
    b = false;
    c = new And(a,b);
    System.out.println(a+" && " + b + " = "+c.getResult());

    a = true;
    b = true;
    c = new And(a,b);
    System.out.println(a+" && " + b + " = "+c.getResult());
  }
}
