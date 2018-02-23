package org.usfirst.frc.team5490.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5490.robot.Robot;
import org.usfirst.frc.team5490.robot.RobotMap;
import org.usfirst.frc.team5490.robot.commands.GripperOpen;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Talon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DigitalInput;


/**
 *
 * The gripper subsystem is a simple system with two "snowblower" motors for opening and closing the gripper bars.
 * It is assumed that 0 is the "open" orientation.
 * 
 */
public class Gripper extends PIDSubsystem {

	// TODO Tuning is needed.
	private static final double kP = 0;
	private static final double kI = 0;
	private static final double tolerance = 0;
	
	// TODO determine mm per turn for grippers
	private static final double mm_per_turn = 4.2;
	private static final double pulses_per_revolution = 500;
	// TODO Determine close direction for grippers, USING THE LEFT GRIPPER (seen from outside)
	private static final double gripper_close_direction = 1;
	
	// Gripper Objects
    private WPI_TalonSRX gripperLeft = new WPI_TalonSRX(RobotMap.mtrGripperLeft);
    private WPI_TalonSRX gripperRight = new WPI_TalonSRX(RobotMap.mtrGripperRight);
    
    public Encoder gripperEncoder = new Encoder(RobotMap.gripperEncoderA,RobotMap.gripperEncoderB);
    
    
    
	public Gripper() {
		super(kP, kI, 0);
		gripperEncoder.setDistancePerPulse(mm_per_turn / pulses_per_revolution);
		setAbsoluteTolerance(tolerance);
		
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new GripperOpen());
    }
    
    public void log() {
    	SmartDashboard.putData("gripperLeft", gripperLeft);
    	SmartDashboard.putData("gripperRight", gripperRight);
    	SmartDashboard.putData("Gripper Encoder (L)", gripperEncoder);
    	
    	// Let's name everything on the LiveWindow
    	addChild("gripperLeft", gripperLeft);
    	addChild("gripperRight", gripperRight);
	}

	
	/**
	 * Set the claw motor to move in the open direction. Percent ranges from 0 to 1.
	 */
	public void open(double percent) {
		gripperLeft.set(percent * -1 * gripper_close_direction);
		gripperRight.set(percent * gripper_close_direction);
	}
	
	// TODO may be eliminated after testing
	public void open(double percentL, double percentR) {
		gripperLeft.set(percentL * -1 * gripper_close_direction);
		gripperRight.set(percentR * gripper_close_direction);
	}

	/**
	 * Set the claw motor to move in the close direction. Percent ranges from 0 to 1.
	 */
	public void close(double percent) {
		gripperLeft.set(percent * gripper_close_direction);
		gripperRight.set(percent * -1 * gripper_close_direction);
	}
	
	// TODO may be eliminated after testing
	public void close(double percentL, double percentR) {
		gripperLeft.set(percentL * gripper_close_direction);
		gripperRight.set(percentR * -1 * gripper_close_direction);
	}

	/**
	 * Stops the claw motor from moving.
	 */
	public void stop() {
		gripperLeft.set(0);
		gripperRight.set(0);
	}

	/**
	 * Return true when the robot is grabbing an object hard enough to trigger
	 * the limit switch.
	 */
	public boolean isGrabbing() {
		return false;
	}

	/**
	 * Return true when the grippers are fully open.
	 */
	public boolean isOpen() {
		return false;
	}

	@Override
	protected double returnPIDInput() {
		return gripperEncoder.getDistance();
	}

	@Override
	protected void usePIDOutput(double output) {
		gripperLeft.set(output * gripper_close_direction);
		gripperRight.set(output * -1 * gripper_close_direction);
	}

	
}

