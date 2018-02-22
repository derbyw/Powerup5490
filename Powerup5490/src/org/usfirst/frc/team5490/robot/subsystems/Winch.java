package org.usfirst.frc.team5490.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

import org.usfirst.frc.team5490.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


/**
 *
 */
public class Winch extends Subsystem {
	
    // Winch objects
    WPI_TalonSRX motorWinch= new WPI_TalonSRX(RobotMap.mtrWinch);
    
    // Define the winch encoder 
    //Encoder 		m_WinchEncoder = new Encoder(RobotMap.WinchEncoderA,RobotMap.WinchEncoderB);
    
    // Limit switches
    private DigitalInput m_lvertical = new DigitalInput(RobotMap.LS_WinchUp);
	private DigitalInput m_lstored = new DigitalInput(RobotMap.LS_WinchDown);


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    	// Define current limiting
    	motorWinch.configContinuousCurrentLimit(10, 0);
    	motorWinch.configPeakCurrentLimit(15, 0);
    	motorWinch.configPeakCurrentDuration(100, 0);
    	motorWinch.enableCurrentLimit(true);    	
    	motorWinch.configOpenloopRamp(2, 0);
    	
		//addChild("Winch Encoder", m_WinchEncoder);	
		
		// ToDo calculate based on Drum size
		//m_WinchEncoder.setDistancePerPulse((4.0 / 12.0 * Math.PI) / 360.0);

    }
    
    public void log() {
    	
    	//SmartDashboard.putNumber("Winch Speed", m_WinchEncoder.getRate());
    	//SmartDashboard.putNumber("Winch Distance", m_WinchEncoder.getDistance());
	}
    
	/**
	 * Set the winch motor to move in the un-spooled direction.
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
		//return m_lvertical.get();		
		return false;
	}
		

	/**
	 * Return true when the which lift triggers the "stored" limit switch.
	 */
	public boolean isLiftStored() {
		//return m_lstored.get();
		return false;
	}


}

