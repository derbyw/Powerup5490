package org.usfirst.frc.team5490.robot.commands;

import org.usfirst.frc.team5490.robot.PathRecord;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Reverse2 extends PathSequence {    
	
    private double speed = 0.4;
	
    public Reverse2() {
    	super();
    	
    	PathRecord[] 	p =  {    			
    			new PathRecord( 0, 1, 0, speed, 1),

        	};
    	path = p;

    }
}
