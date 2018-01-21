/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5490.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	// define the motor mapping
	public static int mtrFrontLeft = 0;
	public static int mtrFrontRight = 1;
	public static int mtrRearRight = 2;
	public static int mtrRearLeft = 3;	
	public static int mtrWinch = 4;	
	public static int mtrGripper = 5;
	public static int mtrLift = 6;
	
	// Encoder map
	// mapping is TBD
	// ToDo -- none of this is confirmed
	public static int FrontLeftEncoder = 0;
	public static int FrontRightEncoder = 1;
	public static int RearRightEncoder = 2;
	public static int RearLeftEncoder = 3;	
	public static int WinchEncoder = 4;
	public static int GripperEncoder = 5;
	
	// Limit switches
	// mapping is TBD
	// ToDo -- none of this is confirmed	
	public static int LS_WinchUp = 1;
	public static int LS_WinchDown = 2;

	public static int LS_LiftUp = 3;
	public static int LS_LiftDown = 4;

	public static int LS_GripperOpen = 5;
	public static int LS_GripperClosed = 6;
}
