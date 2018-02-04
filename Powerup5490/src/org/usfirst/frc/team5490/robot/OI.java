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
import org.usfirst.frc.team5490.robot.commands.LiftSetpoint;
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
	
	private Joystick m_joystick = new Joystick(0);

	
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
		// Put our operations buttons on the SmartDashboard
		
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
		
		JoystickButton lu = new JoystickButton(m_joystick, 9);
		JoystickButton ld = new JoystickButton(m_joystick, 10);
		JoystickButton gopen = new JoystickButton(m_joystick, 2);
		JoystickButton gclose = new JoystickButton(m_joystick, 1);
		JoystickButton wstore = new JoystickButton(m_joystick, 11);
		JoystickButton woperate = new JoystickButton(m_joystick, 12);
		
		
		
		lu.toggleWhenPressed(new LiftSetpoint(1270));
		ld.toggleWhenPressed(new LiftSetpoint(0));
		
		gopen.toggleWhenPressed(new GripperOpen());
		gclose.toggleWhenPressed(new GripperClose());
		
		wstore.toggleWhenPressed(new WinchToStore());
		woperate.toggleWhenPressed(new WinchToOperate());


		// Connect the buttons to commands

	}
	
	public Joystick getJoystick() {
		return m_joystick;
	}
}
