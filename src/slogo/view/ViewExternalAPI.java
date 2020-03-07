package slogo.view;

public interface ViewExternalAPI {
  void update(double newX, double newY, double newAngle);
  void updateCommandPenColor(double value);
  void updateBackgroundColor(double value);
  void updatePenSize(double value);
  void updateShape(double value);
  void clear();
  void updateTurtleView(double value);
  void updatePenStatus(double value);
  void updateStatus();
  void addCommand(String commandSyntax, String syntax);
  void addVariable(String newVariable, Double newValue);
  void setCommandSize(int size);
  void setColorPallete(double id, double red, double green, double blue);
  int getArenaWidth();
  int getArenaHeight();
}
