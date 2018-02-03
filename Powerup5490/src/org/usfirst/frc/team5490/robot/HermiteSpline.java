package org.usfirst.frc.team5490.robot;

//
//Implements a Hermite Cubic Curve
//
//

public class HermiteSpline {
	

	private Point3D[] m_P;
	private Point3D[] mm_T;
	
	
	private Point3D m_Point = new Point3D();
	

	
	public void Size(int points)
	{        
        m_P = new Point3D[points + 1];
        mm_T = new Point3D[points + 1];

        for (int i = 0; i < points; i++)
        {
			if (m_P[i] == null)
            {
                m_P[i] = new Point3D();
            }
            if (mm_T[i] == null)
            {
                mm_T[i] = new Point3D();
            }
        }
	}
	
	
	public Point3D P(short i)
	{
	    return m_P[i];
	}
	
	public Point3D T(short i)
	{
	    return mm_T[i];
	}
	
	// calculate a point in the span of control points i-1..i 
	// i is index of control points
	// t is percentage between points  (i.e. range is 0..1)
	public Point3D Calc(int i, float t)
	{
	    double t2 = 0;
	    double t3 = 0;
	    double c = 0;
	    double a = 0;
	    double b = 0;
	    double d = 0;
	    
	
	    if (i < 1 || i > m_P.length)
	    {	
		    t2 = t * t;
		    t3 = t2 * t;
		    
		    //Curve.x = cx(0) * t3 + cx(1) * t2 + cx(2) * t + cx(3)
		    //Curve.y = cy(0) * t3 + cy(1) * t2 + cy(2) * t + cy(3)
		    //Curve.z = cz(0) * t3 + cz(1) * t2 + cz(2) * t + cz(3)
		
		    a = 2 * t3 - 3 * t2 + 1;		//h1
		    b = -2 * t3 + 3 * t2;			//h2	    	
		    c = t3 - 2 * t2 + t;			//h3
		    d = (t3 - t2); 					//h4
		
		    m_Point.x = a * m_P[i - 1].x + b * m_P[i].x + c * mm_T[i - 1].x + d * mm_T[i].x;
		    m_Point.y = a * m_P[i - 1].y + b * m_P[i].y + c * mm_T[i - 1].y + d * mm_T[i].y;
		    m_Point.z = a * m_P[i - 1].z + b * m_P[i].z + c * mm_T[i - 1].z + d * mm_T[i].z;
	    }
	    
	    return m_Point;	
	}
}
