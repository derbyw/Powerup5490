package org.usfirst.frc.team5490.robot.subsystems;

import org.usfirst.frc.team5490.robot.RobotMap;
import org.usfirst.frc.team5490.robot.commands.LiftDown;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Lift extends PIDSubsystem {

	
    // Winch objects
    private Talon   motorLift = new Talon(RobotMap.mtrLift);   
    private Encoder m_LiftEncoder = new Encoder(RobotMap.LiftEncoderA,RobotMap.LiftEncoderB);
    
     
    //Limit switches
    private DigitalInput m_lsTop = new DigitalInput(RobotMap.LS_LiftUp);
	private DigitalInput m_lsBottom = new DigitalInput(RobotMap.LS_LiftDown);
	
	private static final double kP = 0.01;
	private static final double kI = 0.0;

	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public Lift() {
		super(kP, kI, 0);
		
		
		m_LiftEncoder.setDistancePerPulse(4.88 / 500);   // 4.88 mm per turn of the shaft / pulses per turn
		setAbsoluteTolerance(0.1); // MM
		

		// Let's name everything on the LiveWindow
		addChild("Lift Motor", motorLift);
		addChild("Lift Encoder", m_LiftEncoder);
		addChild("Up Limit Switch", m_lsTop);
		addChild("Down Limit Switch", m_lsBottom);
	}
	
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new LiftDown());
    	 
    }
    
    public void log() {
    	SmartDashboard.putNumber("Lift Speed", m_LiftEncoder.getRate());
    	SmartDashboard.putNumber("Lift Distance", m_LiftEncoder.getDistance());
	}
    
    /**
	 * Set the lift motor to move in the down direction.
	 */
	public void lower() {
		motorLift.set(-0.2);
	}

	/**
	 * Set the lift motor to move in the up direction.
	 */
	public void raise() {
		motorLift.set(.2);
	}

	/**
	 * Stops the lift motor from moving.
	 */
	public void stop() {
		motorLift.set(0);
	}
    
	/**
	 * Return true when the which lift triggers the "top" limit switch.
	 */
	public boolean isAtTop() {
		//return m_lsTop.get();
		return false;
	}

	/**
	 * Return true when the which lift triggers the "bottom" limit switch.
	 */
	public boolean isAtBottom() {
		//return m_lsBottom.get();
		return false;
	}
	
	/**
	 * Use the potentiometer as the PID sensor. This method is automatically
	 * called by the subsystem.
	 */
	@Override
	protected double returnPIDInput() {
		return m_LiftEncoder.getDistance();
	}
	
	
	static double current;
	
	/**
	 * Use the motor as the PID output. This method is automatically called by
	 * the subsystem.
	 */
	@Override
	protected void usePIDOutput(double power) {
		//
		double weight= 1;	// 		
		
		current = (1-weight) * current + (weight * power);
		
		motorLift.set(current*-1);
	}
}

