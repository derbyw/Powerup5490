package org.usfirst.frc.team5490.robot.commands;

import org.usfirst.frc.team5490.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


/**
*
*/
public class LiftToStore extends LiftSetpoint {
	
	//TODO determine exact value for switch
	private static final double storepos = 600; // mm

   public LiftToStore() {
   	
   	super(storepos);
   	
   	requires(Robot.m_Lift);
   }
}
