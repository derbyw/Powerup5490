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
    private SpeedController motorWinch = new WPI_TalonSRX(RobotMap.mtrWinch);
    
    // TODO determine wind direction for winch
    private static final double motor_wind_direction = -1;
    
    // Limit switches
    private DigitalInput m_lvertical = new DigitalInput(RobotMap.ls_winchUp);
	private DigitalInput m_lstored = new DigitalInput(RobotMap.ls_winchDown);


    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void log() {
    	SmartDashboard.putNumber("Winch Speed [-1,1]", motorWinch.get());
	}
    
	/**
	 * Set the winch motor to move in the un-spooled direction. Percent ranges from 0 to 1.
	 */
	public void unwind(double percent) {
		motorWinch.set(percent * -1 * motor_wind_direction);
	}

	/**
	 * Set the winch motor to move in the spooled direction. Percent ranges from 0 to 1.
	 */
	public void wind(double percent) {
		motorWinch.set(percent * motor_wind_direction);
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
		// TODO uncomment this when the limit switch is added
		//return m_lvertical.get();		
		return false;
	}
		

	/**
	 * Return true when the which lift triggers the "stored" limit switch.
	 */
	public boolean isLiftStored() {
		// TODO uncomment this when the limit switch is added
		//return m_lstored.get();
		return false;
	}


}

