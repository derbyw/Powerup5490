package org.usfirst.frc.team5490.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
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
public class Winch extends PIDSubsystem {
	
	// TODO Tuning (obviously is needed)
	private static final double kP = 0;
	private static final double kI = 0;
	private static final double tolerance = 0;
	
	// TODO Encoder Configurations. Testing is needed for motor_up_direction
	private static final double mm_per_turn = 0;
	private static final double pulses_per_revolution = 500;
	private static final double motor_wind_direction = -1;
	
	// Winch objects
    private WPI_TalonSRX motorWinch = new WPI_TalonSRX(RobotMap.mtrWinch);
    private Encoder m_WinchEncoder = new Encoder(RobotMap.WinchEncoderA,RobotMap.WinchEncoderB);

    
    // Limit switches
    private DigitalInput m_lvertical = new DigitalInput(RobotMap.LS_WinchUp);
	private DigitalInput m_lstored = new DigitalInput(RobotMap.LS_WinchDown);

	
    public Winch() {
		super(kP, kI, 0);
		// TODO calculate based on Drum size
		m_WinchEncoder.setDistancePerPulse(((4.0 / 12.0 * Math.PI) / 360.0) / pulses_per_revolution);
		setAbsoluteTolerance(tolerance);
		
		addChild("Winch Encoder", m_WinchEncoder);
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void log() {
    	SmartDashboard.putNumber("Winch Speed", m_WinchEncoder.getRate());
    	SmartDashboard.putNumber("Winch Distance", m_WinchEncoder.getDistance());
	}
    
    
	/**
	 * Set the winch motor to move in the un-spooled direction.
	 */
	public void unwind(double percent) {
		motorWinch.set(percent * -1 * motor_wind_direction);
	}

	/**
	 * Set the winch motor to move in the spooled direction.
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

	/**
	 * Use the magnetic encoder as the PID sensor. This method is automatically
	 * called by the subsystem.
	 */
	@Override
	protected double returnPIDInput() {
		return m_WinchEncoder.getDistance();
	}
	
	/**
	 * Use the motor as the PID output. This method is automatically called by
	 * the subsystem.
	 */
	@Override
	protected void usePIDOutput(double power) {
		motorWinch.set(power*motor_wind_direction);
	}


}

