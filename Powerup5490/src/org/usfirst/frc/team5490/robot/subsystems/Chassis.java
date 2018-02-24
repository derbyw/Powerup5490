package org.usfirst.frc.team5490.robot.subsystems;


import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;


import org.usfirst.frc.team5490.robot.RobotMap;
import org.usfirst.frc.team5490.robot.commands.DriveRobot;
import org.usfirst.frc.team5490.robot.HermiteSpline;


//import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

//import com.analog.adis16448.frc.ADIS16448_IMU;





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
    
    private static HermiteSpline Hcurve;
    
    //ADIS16448_IMU imu = new ADIS16448_IMU();
    
    
    
  
    
    
     
    

    
	

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	// see DriveTrain example
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	
        Hcurve = new HermiteSpline();
        Hcurve.Size(2);
    		
    				// Define point and tangent for point 1 (starting)
        Hcurve.P(0).x = 0;
        Hcurve.P(0).y = 0;
        Hcurve.P(0).z = 0;		// angle
    		
        Hcurve.T(0).x = 50;
        Hcurve.T(0).y = 5;
        Hcurve.T(0).z = 10;		// angle
    		
        Hcurve.P(1).x = 20;
        Hcurve.P(1).y = 15;
        Hcurve.P(1).z = 45 * (Math.PI/180.0);		// angle (in radians)

        Hcurve.T(1).x = 50;
        Hcurve.T(1).y = 0;
        Hcurve.T(0).z = 10;		// angle


		// (2/14/2018) Inversion proved to be necessary to get the robot moving in the right direction 
    	//motorFrontLeft.setInverted(true);
    	//motorRearRight.setInverted(true);
    			
		// Let's name the sensors on the LiveWindow
		
		//addChild("Drive", m_robotDrive);
		//addChild("Winch", m_Winch);
		

		// ToDo determine when the light should come on/off
		//addChild("Lightmast", m_lightmast);

		
		//When no other command is running let the operator drive around using the joystick		 
		setDefaultCommand(new DriveRobot());
		

		/*
		SmartDashboard.putNumber("Gyro-X", imu.getAngleX());
		SmartDashboard.putNumber("Gyro-Y", imu.getAngleY());
		SmartDashboard.putNumber("Gyro-Z", imu.getAngleZ());
		
		SmartDashboard.putNumber("Accel-X", imu.getAccelX());
		SmartDashboard.putNumber("Accel-Y", imu.getAccelY());
		SmartDashboard.putNumber("Accel-Z", imu.getAccelZ());
		
		SmartDashboard.putNumber("Pitch", imu.getPitch());
		SmartDashboard.putNumber("Roll", imu.getRoll());
		SmartDashboard.putNumber("Yaw", imu.getYaw());
		
		SmartDashboard.putNumber("Pressure: ", imu.getBarometricPressure());
		SmartDashboard.putNumber("Temperature: ", imu.getTemperature());
		*/
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
    	
    	SmartDashboard.putNumber("FL", motorFrontLeft.get());
    	SmartDashboard.putNumber("FR", motorFrontRight.get());
    	SmartDashboard.putNumber("RL", motorRearLeft.get());
    	SmartDashboard.putNumber("RR", motorRearRight.get());
    	
    	/*
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
		*/
		
	}

	
	public void Drive(Joystick driveStick)
	{
		double speedrange = 1 - minimum_drive;
		double speed = (-speedrange*driveStick.getThrottle()+1)/2;
		speed += minimum_drive;

		m_robotDrive.setDeadband(0.2);
		
		// mechanum orientation is
		//  X forward/reverse
		//	Y right/left
		//  Z twist		
		// normal people assume
		//  Y forward/reverse
		//	X right/left
		//  Z twist
		//  So we swap orientations here to avoid brain cramps..
		//
		m_robotDrive.driveCartesian(speed*driveStick.getX(),speed*driveStick.getY(),speed*driveStick.getZ(), 0);
		
		//
		
	}
	
	// Let an external function drive the chassis
	// note X = forward, Y right 
	// 
	public void Drive(double X, double Y, double Z, double speed)
	{
		// mechanum orientation is
		//  X forward/reverse
		//	Y right/left
		//  Z twist		
		// normal people assume
		//  Y forward/reverse
		//	X right/left
		//  Z twist
		//  So we swap orientations here to avoid brain cramps..
		//
		m_robotDrive.driveCartesian(X * speed, Y * speed, Z * speed, 0);
	}
	
	public void StopMotors()
	{		
		m_robotDrive.driveCartesian(0,0,0,0);		
	}
	
	public void moveForward()
	{
		m_robotDrive.driveCartesian(0,1,0,0);
	}
	
	public void moveBackward()
	{
		m_robotDrive.driveCartesian(0,-1,0,0);
	}
}

