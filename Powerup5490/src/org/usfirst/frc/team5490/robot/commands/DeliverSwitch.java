package org.usfirst.frc.team5490.robot.commands;

import org.usfirst.frc.team5490.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DeliverSwitch extends CommandGroup {

    public DeliverSwitch() {
    	requires(Robot.m_Lift);
    	requires(Robot.m_Chassis);
    	requires(Robot.m_Gripper);
    	
    	addSequential(new LiftSwitch());
    	addSequential(new Forward2());
    	addSequential(new GripperOpen());
    	addSequential(new Reverse2());
    	addSequential(new LiftDown());
    }
}
