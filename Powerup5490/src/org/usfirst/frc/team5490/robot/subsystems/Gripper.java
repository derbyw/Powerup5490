package org.usfirst.frc.team5490.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team5490.robot.RobotMap;
import org.usfirst.frc.team5490.robot.commands.GripperOpen;

import edu.wpi.first.wpilibj.Talon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DigitalInput;


/**
 *
 * The gripper subsystem is a simple system with two "snowblower" motors for opening and closing the gripper bars
 * 
 */
public class Gripper extends Subsystem {

	// note sure if we need another pair (i.e. a set for both arms
	private DigitalInput m_lopen = new DigitalInput(RobotMap.LS_GripperOpen);
	private DigitalInput m_lclose = new DigitalInput(RobotMap.LS_GripperClosed);
	
    Talon mComboGripper = new Talon(RobotMap.mtrGripper);
    
    Encoder 		LGripperEncoder;
    Encoder 		RGripperEncoder;
    
	public Gripper() {
		super();

		// Let's name everything on the LiveWindow
		addChild("Motor", mComboGripper);
		addChild("Open Limit Switch", m_lopen);
		addChild("Close Limit Switch", m_lclose);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new GripperOpen());
    }
    
    public void log() {
	}

	
	/**
	 * Set the claw motor to move in the open direction.
	 */
	public void open() {
		mComboGripper.set(-1);
	}

	/**
	 * Set the claw motor to move in the close direction.
	 */
	public void close() {
		mComboGripper.set(1);
	}

	/**
	 * Stops the claw motor from moving.
	 */
	public void stop() {
		mComboGripper.set(0);
	}

	/**
	 * Return true when the robot is grabbing an object hard enough to trigger
	 * the limit switch.
	 */
	public boolean isGrabbing() {
		return m_lclose.get();
	}

	public boolean isOpen() {
		return m_lopen.get();
	}

	
}

