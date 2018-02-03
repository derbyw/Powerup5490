package org.usfirst.frc.team5490.robot.commands;

import org.usfirst.frc.team5490.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Move the elevator to a given location. This command finishes when it is
 * within the tolerance, but leaves the PID loop running to maintain the
 * position. Other commands using the elevator should make sure they disable
 * PID!
 */
public class LiftSetpoint extends Command {
	private double m_setpoint;

	public LiftSetpoint(double setpoint) {
		m_setpoint = setpoint;
		requires(Robot.m_Lift);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.m_Lift.enable();
		Robot.m_Lift.setSetpoint(m_setpoint);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return Robot.m_Lift.onTarget();
	}
}
