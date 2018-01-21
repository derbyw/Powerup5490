/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5490.robot;

import org.usfirst.frc.team5490.robot.commands.WinchToOperate;
import org.usfirst.frc.team5490.robot.commands.WinchToStore;

import org.usfirst.frc.team5490.robot.commands.GripperOpen;
import org.usfirst.frc.team5490.robot.commands.GripperClose;
import org.usfirst.frc.team5490.robot.commands.GripperRelease;

import org.usfirst.frc.team5490.robot.commands.LiftDown;
import org.usfirst.frc.team5490.robot.commands.LiftSwitch;
import org.usfirst.frc.team5490.robot.commands.LiftScale;
import org.usfirst.frc.team5490.robot.commands.LiftHook;
import org.usfirst.frc.team5490.robot.commands.LiftRobot;   // same as lift down, but may be partial




import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	private Joystick m_joystick = new Joystick(1);

	
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	public OI() {
		// Put our operatons Some buttons on the SmartDashboard
		
		SmartDashboard.putData("Winch to Operate", new WinchToOperate());
		SmartDashboard.putData("Winch to Store", new WinchToStore());
		
		SmartDashboard.putData("Gripper Close", new GripperClose());
		SmartDashboard.putData("Gripper Release", new GripperRelease());
		SmartDashboard.putData("Gripper Open", new GripperOpen());
		
		SmartDashboard.putData("Lift Down", new LiftDown());
		SmartDashboard.putData("Lift Switch", new LiftSwitch());
		SmartDashboard.putData("Lift Scale", new LiftScale());
		SmartDashboard.putData("Lift Hook", new LiftHook());
		SmartDashboard.putData("Lift Robot", new LiftRobot());
		
		
		// Create some buttons
		/*
		JoystickButton dpadUp = new JoystickButton(m_joystick, 5);
		JoystickButton dpadRight = new JoystickButton(m_joystick, 6);
		JoystickButton dpadDown = new JoystickButton(m_joystick, 7);
		JoystickButton dpadLeft = new JoystickButton(m_joystick, 8);
		JoystickButton l2 = new JoystickButton(m_joystick, 9);
		JoystickButton r2 = new JoystickButton(m_joystick, 10);
		JoystickButton l1 = new JoystickButton(m_joystick, 11);
		JoystickButton r1 = new JoystickButton(m_joystick, 12);

		// Connect the buttons to commands
		
		dpadUp.whenPressed(new SetElevatorSetpoint(0.2));
		dpadDown.whenPressed(new SetElevatorSetpoint(-0.2));
		dpadRight.whenPressed(new CloseClaw());
		dpadLeft.whenPressed(new OpenClaw());

		r1.whenPressed(new PrepareToPickup());
		r2.whenPressed(new Pickup());
		l1.whenPressed(new Place());
		l2.whenPressed(new Autonomous());
		*/
	}
	
	public Joystick getJoystick() {
		return m_joystick;
	}
}
