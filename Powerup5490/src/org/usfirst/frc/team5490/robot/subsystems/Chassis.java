package org.usfirst.frc.team5490.robot.subsystems;


import edu.wpi.first.wpilibj.drive.MecanumDrive;
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
    
    Encoder 		m_WinchEncoder;
    
    //Limit switches
    private DigitalInput m_lvertical = new DigitalInput(RobotMap.LS_WinchUp);
	private DigitalInput m_lstored = new DigitalInput(RobotMap.LS_WinchDown);
    

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
		
		motorFrontLeft.setInverted(true);
		motorRearLeft.setInverted(true);
    }
    
    public void log() {
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

