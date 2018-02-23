 package org.usfirst.frc.team5490.robot.commands;

import org.usfirst.frc.team5490.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftSwitch extends LiftSetpoint {
	
	private static final double switchpos = 400; // mm

    public LiftSwitch() {
    	
    	super(switchpos);
    	
    	requires(Robot.m_Lift);
    }
}
