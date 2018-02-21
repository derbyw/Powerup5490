package org.usfirst.frc.team5490.robot.commands;

import org.usfirst.frc.team5490.robot.EnhancedPIDSetpoint;
import org.usfirst.frc.team5490.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * Move the gripper to a given angle. It is assumed that 0 is the "open" orientation. 
 * This command finishes when it is within the tolerance, but leaves the PID loop 
 * running to maintain the position. Other commands using the lift should make sure 
 * they disable PID!
 * 
 * <p>NOTES - any or all will cause mayhem with the control..
 * 
 * <p> When things go wrong with the control, check:
 * <pre>
 * encoder wires, connected
 * encoder wires swapped (A/B)
 * motor wires swapped (A/B)
 * </pre>
 */

public class GripperSetPoint extends Command {

	private static PIDSubsystem pid_subsystem = Robot.m_Gripper;
	//TODO test & change values for gripper's PID active range and ramp length
	private static double pid_active_range = 20;
	private static int ramp_length = 10;
	
	private double m_setpoint;
	
	private EnhancedPIDSetpoint enhancedPID = 
			new EnhancedPIDSetpoint(pid_subsystem, pid_active_range, ramp_length);
	
	public GripperSetPoint(double setpoint) {
		m_setpoint = setpoint;
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		enhancedPID.initialize(m_setpoint);
	}
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		enhancedPID.execute();
	}
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return enhancedPID.isFinished();
	}
	
}
