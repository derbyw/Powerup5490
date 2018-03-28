package org.usfirst.frc.team5490.robot.commands;

import org.usfirst.frc.team5490.robot.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;

public abstract class AutonomousBase extends CommandGroup {

	public enum AutoStart {
		Left,
		Center,
		Right,
	}
	
	public AutoStart state;
	public String gameData;
	
    public AutonomousBase() {
    	requires(Robot.m_Chassis);
    	requires(Robot.m_Winch);
    	requires(Robot.m_Lift);
    	
    	gameData ="RRR";
    	Robot.m_Chassis.ResetGyro();
    	
    }
}
