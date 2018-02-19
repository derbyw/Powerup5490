package org.usfirst.frc.team5490.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team5490.robot.RobotMap;
import org.usfirst.frc.team5490.robot.commands.GripperOpen;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Talon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DigitalInput;


/**
 *
 * The gripper subsystem is a simple system with two "snowblower" motors for opening and closing the gripper bars
 * 
 */
public class Gripper extends PIDSubsystem {

	// Tuning is needed
	private static final double kP = 0;
	private static final double kI = 0;
	
	// note sure if we need another pair (i.e. a set for both arms
	private DigitalInput m_lopen = new DigitalInput(RobotMap.LS_GripperOpen);
	private DigitalInput m_lclose = new DigitalInput(RobotMap.LS_GripperClosed);
	
	// Gripper Objects
    private WPI_TalonSRX gripperLeft = new WPI_TalonSRX(RobotMap.mtrGripperLeft);
    
    
    Encoder LGripperEncoder;
    Encoder RGripperEncoder;
    
	public Gripper() {
		super(kP, kI, 0);

		// Let's name everything on the LiveWindow
		//addChild("Motor", gripperLeft);
		addChild("Open Limit Switch", m_lopen);
		addChild("Close Limit Switch", m_lclose);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new GripperOpen());
    }
    
    public void log() {
	}

	
	/**
	 * Set the claw motor to move in the open direction.
	 */
	public void open(double percent) {
		gripperLeft.set(percent * -1);
	}

	/**
	 * Set the claw motor to move in the close direction.
	 */
	public void close(double percent) {
		gripperLeft.set(percent);
	}

	/**
	 * Stops the claw motor from moving.
	 */
	public void stop() {
		gripperLeft.set(0);
	}

	/**
	 * Return true when the robot is grabbing an object hard enough to trigger
	 * the limit switch.
	 */
	public boolean isGrabbing() {
		//return m_lclose.get();
		return false;
	}

	public boolean isOpen() {
		//return m_lopen.get();
		return false;
	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		
	}

	
}

