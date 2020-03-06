package slogo.model.command;

import slogo.model.Turtle;

import java.util.List;

public class Sine extends Command {

  /**
   * Default Sine constructor, calls super constuctor
   * and sets the value to return as the sine of the parameter;
   * performs sin(a)
   * @param turtleList the list of turtles being brought in to use this command (if needed)
   * @param doubleList the list of doubles to be used for this command (if needed)
   * @param commandList the list of commands being used for this command (if needed)
   */
  public Sine(List<Turtle> turtleList, List<Double> doubleList, List<List<Command>> commandList){
    super(Math.sin(doubleList.get(FIRST_INDEX)));
  }
}
