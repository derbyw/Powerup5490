package org.usfirst.frc.team5490.robot;



public class PathRecord {	
	public double X;
	public double Y;
	public double Z;
	public double speed;
	public double duration;
	
	public static final double Off = 0;
	public static final double Fwd = 1;
	public static final double Rev = -1;
	public static final double Right = 1;
	public static final double Left = -1;
	public static final double CW = 1;
	public static final double CCW = -1;
	
	public static final double deg90 = 0.4;		// speed to turn 90deg in time90
	public static final double time90 = 1;		// time to run 90deg at deg90
	
	public PathRecord(double X, double Y, double Z, double speed, double duration)
	{
		this.X =X;
		this.Y =Y;
		this.Z =Z;
		this.speed = speed;
		this.duration =duration;
	}
}
