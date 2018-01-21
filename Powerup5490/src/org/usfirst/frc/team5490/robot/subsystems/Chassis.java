package org.usfirst.frc.team5490.robot.subsystems;


import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

import org.usfirst.frc.team5490.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;


import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Chassis extends Subsystem {
	
	// Main Movement Drive 
	
	SpeedController motorFrontLeft = new Talon(RobotMap.mtrFrontLeft);
    SpeedController motorRearLeft= new Talon(RobotMap.mtrRearLeft);  
    SpeedController motorFrontRight= new Talon(RobotMap.mtrFrontRight);
    SpeedController motorRearRight = new Talon(RobotMap.mtrRearRight);
    
    MecanumDrive m_robotDrive = new MecanumDrive(motorFrontLeft,motorRearLeft,motorFrontRight,motorRearRight);
    
    // Winch objects
    SpeedController motorWinch = new Talon(RobotMap.mtrWinch);
    
    // Define the winch encoder 
    Encoder 		m_WinchEncoder = new Encoder(RobotMap.WinchEncoderA,RobotMap.WinchEncoderB);
    
    // Limit switches
    private DigitalInput m_lvertical = new DigitalInput(RobotMap.LS_WinchUp);
	private DigitalInput m_lstored = new DigitalInput(RobotMap.LS_WinchDown);
    

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	// see DriveTrain example
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
		
		motorFrontLeft.setInverted(true);
		motorRearLeft.setInverted(true);
		
		// Let's name the sensors on the LiveWindow
		
		addChild("Drive", m_robotDrive);
		addChild("Winch Encoder", m_WinchEncoder);
		
		
		// ToDo calculate based on Drum size
		m_WinchEncoder.setDistancePerPulse((4.0 / 12.0 * Math.PI) / 360.0);
		
		
		//addChild("Gyro", m_gyro);
		
    }
    
    public void log() {
    	
    	SmartDashboard.putNumber("Winch Speed", m_WinchEncoder.getRate());
    	SmartDashboard.putNumber("Winch Distance", m_WinchEncoder.getDistance());
    	
		/* example
		SmartDashboard.putNumber("Left Distance", m_leftEncoder.getDistance());		 
		SmartDashboard.putNumber("Right Distance", m_rightEncoder.getDistance());
		SmartDashboard.putNumber("Left Speed", m_leftEncoder.getRate());
		SmartDashboard.putNumber("Right Speed", m_rightEncoder.getRate());
		SmartDashboard.putNumber("Gyro", m_gyro.getAngle());
		*/
	}

	public void Drive(Joystick driveStick)
	{
		double speed = (-1*driveStick.getThrottle()+1)/2;
		m_robotDrive.driveCartesian(speed*driveStick.getY(), -speed*driveStick.getX(), speed*driveStick.getTwist(), 0);
	}
	
	/**
	 * Set the winch motor to move in the unspooled direction.
	 */
	public void unwind() {
		motorWinch.set(-1);
	}

	/**
	 * Set the winch motor to move in the spooled direction.
	 */
	public void wind() {
		motorWinch.set(1);
	}

	/**
	 * Stops the winch motor from moving.
	 */
	public void stop() {
		motorWinch.set(0);
	}

	/**
	 * Return true when the which lift triggers the "vertical" limit switch.
	 */
	public boolean isLiftVertical() {
		return m_lvertical.get();
	}

	/**
	 * Return true when the which lift triggers the "stored" limit switch.
	 */
	public boolean isLiftStored() {
		return m_lstored.get();
	}

	
}

