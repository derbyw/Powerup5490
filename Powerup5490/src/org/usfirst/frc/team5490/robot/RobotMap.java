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

	// The following mapping is final as of 1/29/18
	public static int mtrFrontLeft = 0;
	public static int mtrFrontRight = 1;
	public static int mtrRearRight = 2;
	public static int mtrRearLeft = 3;	
	public static int mtrWinch = 4;	
	public static int mtrGripper = 6;
	public static int mtrLift = 5;
	
	// Encoder map
	// mapping is TBD
	// ToDo -- none of this is confirmed till things are wired
	public static int WinchEncoderA = 10;
	public static int WinchEncoderB = 12;
	public static int LiftEncoderA = 13;
	public static int LiftEncoderB = 14;	
	public static int GripperEncoderA = 15;
	public static int GripperEncoderB = 16;
	
	
	
	
	// Limit switches
	// mapping is TBD
	// ToDo -- none of this is confirmed	
	public static int LS_WinchUp = 8;
	public static int LS_WinchDown = 9;

	public static int LS_LiftUp = 3;
	public static int LS_LiftDown = 4;

	public static int LS_GripperOpen = 5;
	public static int LS_GripperClosed = 6;
	
	
	
}
