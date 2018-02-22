package org.usfirst.frc.team5490.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5490.robot.Robot;
import org.usfirst.frc.team5490.robot.RobotMap;
import org.usfirst.frc.team5490.robot.commands.GripperOpen;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;



/**
 *
 * The gripper subsystem is a simple system with two "snowblower" motors for opening and closing the gripper bars
 * 
 */
public class Gripper extends PIDSubsystem {

	
	private static final double large_gear_teeth = 90;
	private static final double small_gear_teeth = 15;
	private static final double deg_per_turn = 360;
	private static final double pulses_per_revolution = 500;
	
	private static final double motor_cw_direction = 1;

	// note sure if we need another pair (i.e. a set for both arms
	private DigitalInput m_lopen = new DigitalInput(RobotMap.LS_GripperOpen);
	private DigitalInput m_lclose = new DigitalInput(RobotMap.LS_GripperClosed);
	
	WPI_TalonSRX mLGripper = new WPI_TalonSRX(RobotMap.mtrLGripper);
	WPI_TalonSRX mRGripper = new WPI_TalonSRX(RobotMap.mtrRGripper);
	
	private static final double kP = 0.003;
	private static final double kI = 0.0001;
        
    private Encoder LGripperEncoder = new Encoder(RobotMap.GripperEncoderA,RobotMap.GripperEncoderB);
    
	
	public Gripper() {
		super(kP, kI, 0);
		
		setAbsoluteTolerance(0.1); // deg		
		
    	// Define current limiting
		mLGripper.configContinuousCurrentLimit(4, 0);
		mLGripper.configPeakCurrentLimit(8, 0);
		mLGripper.configPeakCurrentDuration(100, 0);
		mLGripper.enableCurrentLimit(true);
		mLGripper.configOpenloopRamp(0, 0);
		
		mRGripper.configContinuousCurrentLimit(4, 0);
		mRGripper.configPeakCurrentLimit(8, 0);
		mRGripper.configPeakCurrentDuration(100, 0);
		mRGripper.enableCurrentLimit(true);
	    mRGripper.configOpenloopRamp(0, 0);
		mRGripper.setInverted(true);
		
		// slave the Right gripper to the left
		mRGripper.set(ControlMode.Follower, 2);


		LGripperEncoder.setDistancePerPulse(deg_per_turn / ((large_gear_teeth / small_gear_teeth) * pulses_per_revolution));   // degrees per pulse


		

		// Let's name everything on the LiveWindow
		addChild("LMotor", mLGripper);
		addChild("RMotor", mRGripper);
		
		addChild("Gripper Encoder", LGripperEncoder);
		
		addChild("Open Limit Switch", m_lopen);
		addChild("Close Limit Switch", m_lclose);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new GripperOpen());
    }
    
    public void log() {
    	SmartDashboard.putNumber("Gripper Speed", LGripperEncoder.getRate());
    	SmartDashboard.putNumber("Gripper Distance", LGripperEncoder.getDistance());
	}

	
	/**
	 * Set the claw motor to move in the open direction.
	 */
	public void manual_open() {
		disable();
		mLGripper.set(0.1);
		mRGripper.set(0.1);
	}

	/**
	 * Set the claw motor to move in the close direction.
	 */
	public void manual_close() {
		disable();
		mLGripper.set(-0.1);
		//mRGripper.set(-0.1);
	}

	/**
	 * Stops the claw motor from moving.
	 */
	public void manual_stop() {
		disable();
		mLGripper.set(0);
		//mRGripper.set(0);
	}

	/**
	 * Return true when the robot is grabbing an object hard enough to trigger
	 * the limit switch.
	 */
	public boolean isGrabbing() {
		return (LGripperEncoder.getDistance() < 0);
	}

	public boolean isOpen() {
		return (LGripperEncoder.getDistance() > 90);
		
	}
	
	
	public double RelativeTarget(double delta) {		 
		 return LGripperEncoder.getDistance() + delta;
	}
	
	/**
	 * Use the potentiometer as the PID sensor. This method is automatically
	 * called by the subsystem.
	 */
	@Override
	protected double returnPIDInput() {
		return LGripperEncoder.getDistance();
	}
	
	/**
	 * Use the motor as the PID output. This method is automatically called by
	 * the subsystem.
	 */
	@Override
	protected void usePIDOutput(double power) {
		double output = power*motor_cw_direction; 
		mLGripper.set(output);
		//mRGripper.set(output);
	}

	
}

