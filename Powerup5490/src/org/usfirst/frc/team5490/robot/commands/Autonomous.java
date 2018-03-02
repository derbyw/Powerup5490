package org.usfirst.frc.team5490.robot.commands;

import org.usfirst.frc.team5490.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public abstract class Autonomous extends Command {

	public enum AutoStart {
		Left,
		Center,
		Right,
	}
	
	private AutoStart state;
	public String gameData;
	
    public Autonomous() {
    	requires(Robot.m_Chassis);
    	requires(Robot.m_Lift);
    	
    }
}
