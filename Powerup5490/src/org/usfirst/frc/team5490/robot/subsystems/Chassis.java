package org.usfirst.frc.team5490.robot.subsystems;


import edu.wpi.first.wpilibj.drive.MecanumDrive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;


import org.usfirst.frc.team5490.robot.RobotMap;
import org.usfirst.frc.team5490.robot.commands.DriveRobot;

import org.usfirst.frc.team5490.robot.Point3D;

import org.usfirst.frc.team5490.robot.HermiteSpline;


//import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.analog.adis16448.frc.ADIS16448_IMU;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;





/**
 *
 */
public class Chassis extends Subsystem {
	
	// lower limit for speed setting  
	private static final double minimum_drive = 0.25;
	
	// Main Movement Drive 
	SpeedController motorFrontLeft = new Talon(RobotMap.mtrFrontLeft);
	SpeedController motorRearLeft = new Talon(RobotMap.mtrRearLeft);
    SpeedController motorFrontRight= new Talon(RobotMap.mtrFrontRight);
    SpeedController motorRearRight = new Talon(RobotMap.mtrRearRight);
    
    MecanumDrive m_robotDrive = new MecanumDrive(motorFrontLeft,motorRearLeft,motorFrontRight,motorRearRight);
    
    
    
	public DigitalInput m_lsConfigA = new DigitalInput(RobotMap.configStartA);
	public DigitalInput m_lsConfigB = new DigitalInput(RobotMap.configStartB);
	
	
	

    
    
    private static HermiteSpline JoystickCurve;
    
    private static HermiteSpline Hcurve;
    
    //ADIS16448_IMU imu = new ADIS16448_IMU();
    public ADXRS450_Gyro gyro = new ADXRS450_Gyro();
    
    
    public int segment;
    public double percent;
    public double tick; 
    
    

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	// see DriveTrain example
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	
    	DefineJoystickResponse();
    	
    	gyro.calibrate();
    	gyro.reset();

		
		//When no other command is running let the operator drive around using the joystick		 
		setDefaultCommand(new DriveRobot());
		SmartDashboard.putNumber("Path Segment: ", segment);
		SmartDashboard.putNumber("Path percent: ", percent);
		SmartDashboard.putNumber("Path timer: ", tick);
		 
		

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
		
		SmartDashboard.putNumber("Gyro-X", gyro.getAngle());		
    }
    
    
    // returns the jumper configuration used to indicate which
    public RobotMap.StartPositions GetStartPlacement()    
	{
    	
    	int select = 0;
    	if (m_lsConfigA.get()) select += 1;
    	if (m_lsConfigB.get()) select += 2;
    	//if (m_lsConfigC.get()) select += 4;
    	
    	switch(select) {
    		case 1:		// 01 - center
    		    return RobotMap.StartPositions.Right;
    		case 2:		// 10  - right
    		    return RobotMap.StartPositions.Left;
    		default:	// 00, 11 - left
    		    return RobotMap.StartPositions.Center;    		
    	}
	}
    
    private void DefineJoystickResponse()
    {
    	JoystickCurve = new HermiteSpline();
    	JoystickCurve.Size(2);
		
				// Define point and tangent for point 1 (starting)
    	JoystickCurve.P(0).x = 0;
    	JoystickCurve.P(0).y = 0;
    	JoystickCurve.P(0).z = 0;		// angle
		
    	JoystickCurve.T(0).x = 50;
    	JoystickCurve.T(0).y = 0;
    	JoystickCurve.T(0).z = 0;		// angle
		
    	JoystickCurve.P(1).x = 1;
    	JoystickCurve.P(1).y = 1;
    	JoystickCurve.P(1).z = 1;

    	JoystickCurve.T(1).x = -15;
    	JoystickCurve.T(1).y = 0;
    	JoystickCurve.T(0).z = 0;		// angle
    }
    
   
    public void log() 
    {
    	

    	
    	
    	SmartDashboard.putNumber("FL", -1 * motorFrontLeft.get());
    	SmartDashboard.putNumber("FR", motorFrontRight.get());
    	SmartDashboard.putNumber("RL", -1 * motorRearLeft.get());
    	SmartDashboard.putNumber("RR", motorRearRight.get());

		SmartDashboard.putNumber("Path Segment: ", segment);
		SmartDashboard.putNumber("Path percent: ", percent);
		SmartDashboard.putNumber("Path timer: ", tick);

		SmartDashboard.putNumber("Gyro-Angle", gyro.getAngle());
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
		
		SmartDashboard.putNumber("Angle-X", imu.getAngleX());
		SmartDashboard.putNumber("Angle-Y", imu.getAngleY());
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
		
		/*
		double Xreq = -1 * driveStick.getX();
		double Yreq = driveStick.getY();
		double Zreq = driveStick.getX();
		
		// 
		// make a non-linear response to the joystick that smoothes out
		// initial response - this keeps twist from doing weird things
		// use same response for each axis...
		// the curve works for 0..1 so remove polarity for calc 
		Point3D xscale = JoystickCurve.Calc(1, Math.abs(Xreq));
		Point3D yscale = JoystickCurve.Calc(1, Math.abs(Yreq));
		Point3D zscale = JoystickCurve.Calc(1, Math.abs(Zreq));
		
		double Xout = speed * xscale.y;
		double Yout = speed * yscale.y;
		double Zout = speed * zscale.y;
		
		// restore polarity
		if (Xreq < 0) Xout *= -1;
		if (Yreq < 0) Yout *= -1;
		if (Zreq < 0) Zout *= -1;
		
		
		m_robotDrive.driveCartesian(Xout, Yout, Zout, 0);
		*/
		
		m_robotDrive.driveCartesian(-1 * speed*driveStick.getX(),
									speed*driveStick.getY(),
									-0.5*driveStick.getZ(), 0);
									
		
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
		if (X > 1.0) X = 1.0;
		if (Y > 1.0) Y = 1.0;
		if (Z > 1.0) Z = 1.0;
		if (speed > 1.0) speed = 1.0;
		
		//m_robotDrive.driveCartesian( -1 * X * speed, -1 * Y * speed, Z * speed, imu.getAngleY());
		m_robotDrive.driveCartesian( -1 * X * speed, -1 * Y * speed, Z * speed, 0);
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
	
	public void ResetGyro()
	{		
		gyro.reset();
	}
}

