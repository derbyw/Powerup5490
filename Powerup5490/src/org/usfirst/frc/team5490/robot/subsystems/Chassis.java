package org.usfirst.frc.team5490.robot.subsystems;


import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;

import org.usfirst.frc.team5490.robot.RobotMap;
import org.usfirst.frc.team5490.robot.commands.DriveRobot;


//import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.analog.adis16448.frc.ADIS16448_IMU;




/**
 *
 */
public class Chassis extends Subsystem {
	
	// lower limit for speed setting  
	private static final double minimum_drive = 0.1;
	
	// Main Movement Drive 
	SpeedController motorFrontLeft = new Talon(RobotMap.mtrFrontLeft);
	SpeedController motorRearLeft = new Talon(RobotMap.mtrRearLeft);
    SpeedController motorFrontRight= new Talon(RobotMap.mtrFrontRight);
    SpeedController motorRearRight = new Talon(RobotMap.mtrRearRight);
    
	//RobotDrive m_robotDrive = new RobotDrive(motorFrontLeft, motorRearLeft, motorFrontRight, motorRearRight);
    MecanumDrive m_robotDrive = new MecanumDrive(motorFrontLeft,motorRearLeft,motorFrontRight,motorRearRight);
    
    public Winch m_Winch = new Winch();
    
    ADIS16448_IMU imu = new ADIS16448_IMU();
    

    /*
     *   
    Hcurve.Size = 9;


    i = 0; // at pad
    Hcurve.P(i).x = m.pad_depth;
    Hcurve.P(i).y = pheight;
    Hcurve.r(i).x = (theight - pheight) * 2;
    Hcurve.r(i).y = 0;
    */
    
    /*
     * (non-Javadoc)
     * @see edu.wpi.first.wpilibj.command.Subsystem#initDefaultCommand()
     */
    
	
	//private DigitalOutput m_lightmast = new	DigitalOutput(RobotMap.out_Lightmast);
    

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	// see DriveTrain example
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.

		// (2/14/2018) Inversion proved to be necessary to get the robot moving in the right direction 
    	motorFrontLeft.setInverted(true);
    	motorRearRight.setInverted(true);
    			
		// Let's name the sensors on the LiveWindow
		
		//addChild("Drive", m_robotDrive);
		//addChild("Winch", m_Winch);
		

		// ToDo determine when the light should come on/off
		//addChild("Lightmast", m_lightmast);

		
		//When no other command is running let the operator drive around using the joystick		 
		setDefaultCommand(new DriveRobot());
		
    }
    
   
    public void log() 
    {
    	
    	// put class variables we want to see on dashboard or capture here
    	
		/* example
		SmartDashboard.putNumber("Left Distance", m_leftEncoder.getDistance());		 
		SmartDashboard.putNumber("Right Distance", m_rightEncoder.getDistance());
		SmartDashboard.putNumber("Left Speed", m_leftEncoder.getRate());
		SmartDashboard.putNumber("Right Speed", m_rightEncoder.getRate());
		SmartDashboard.putNumber("Gyro", m_gyro.getAngle());
		*/
    	
    	m_Winch.log();
    	
//    	SmartDashboard.putData("IMU board", imu);
    	
    	SmartDashboard.putNumber("FL", -1* motorFrontLeft.get());
    	SmartDashboard.putNumber("FR", motorFrontRight.get());
    	SmartDashboard.putNumber("RL", -1 * motorRearLeft.get());
    	SmartDashboard.putNumber("RR", motorRearRight.get());
    	
		SmartDashboard.putNumber("Gyro-X", imu.getAngleX());
		SmartDashboard.putNumber("Gyro-Y", imu.getAngleY());
		SmartDashboard.putNumber("Gyro-Z", imu.getAngleZ());
		
		SmartDashboard.putNumber("Accel-X", imu.getAccelX());
		SmartDashboard.putNumber("Accel-Y", imu.getAccelY());
		SmartDashboard.putNumber("Accel-Z", imu.getAccelZ());
		
		SmartDashboard.putNumber("Pitch", imu.getPitch());
		SmartDashboard.putNumber("Roll", imu.getRoll());
		SmartDashboard.putNumber("Yaw", imu.getYaw());
		
		SmartDashboard.putNumber("Angle-Z", imu.getAngleZ());
		
		SmartDashboard.putNumber("Pressure: ", imu.getBarometricPressure());
		SmartDashboard.putNumber("Temperature: ", imu.getTemperature());
		
	}

	@SuppressWarnings("deprecation")
	public void Drive(Joystick driveStick)
	{
		double speedrange = 1 - minimum_drive;
		double speed = (-speedrange*driveStick.getThrottle()+1)/2;
		speed += minimum_drive;
		
		//  this is *supposed* to be this --  
		//driveCartesian(double ySpeed, double xSpeed, double zRotation, double gyroAngle)
		// we had
		//m_robotDrive.driveCartesian(speed*driveStick.getTwist(), -speed*driveStick.getX(), speed*driveStick.getY(), imu.getYaw());
		//
		// this *should* be right -- if not investigate motor wiring & config 
		
		// ADD SPEED LIMIT LATER
		// TODO IMPORTANT: when the joystick is sent tilted little bit to the back, the rear wheels go forward slowly; may not be an issue on the new robot
		m_robotDrive.setDeadband(0.2);
		m_robotDrive.driveCartesian(-speed*driveStick.getY(),speed*driveStick.getX(),speed*driveStick.getZ(), imu.getAngleZ());
		
		//m_robotDrive.arcadeDrive(driveStick);
		
	}
	
	// Let an external function drive the chassis 
	public void Drive(double X, double Y, double Z, double speed)
	{
		// TODO need to set something to reset the IMU board before the autonomous period
		m_robotDrive.driveCartesian(-Y* speed, X * speed, Z * speed, imu.getAngleZ());
	}
	
	public void StopMotors()
	{		
		m_robotDrive.driveCartesian(0,0,0,0);		
	}
	
	public void moveForward()
	{
		motorFrontLeft.set(-1);
		motorRearLeft.set(-1);
		motorFrontRight.set(1);
		motorRearRight.set(1);
	}
	
	public void moveBackward()
	{
		motorFrontLeft.set(1);
		motorRearLeft.set(1);
		motorFrontRight.set(-1);
		motorRearRight.set(-1);
	}
}

