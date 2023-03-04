package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Hardware {
    //drive motor declarations
    public DcMotorEx LeftDriveRear = null;
    public DcMotorEx RightDriveRear = null;
    public DcMotorEx LeftDriveFront = null;
    public DcMotorEx RightDriveFront = null;

    //lift motor declarations
    public DcMotorEx RightLift = null;
    public DcMotorEx LeftLift = null;

    //claw servo declarations
    public Servo RightClaw = null;
    public Servo LeftClaw = null;

    //ticks per second
    public double LiftUpVelocity = 300;
    public double LiftDownVelocity = 300;

    //servo positions
    public double RightClawOpen = 1;
    public double LeftClawOpen = 1;
    public double RightClawClosed = 0;
    public double LeftClawClosed = 0;


    public void CalculateCOUNTS_PER_MOTOR_REV(double cpr, double gearRedox, double wheelDiameter){
        //encoders values for COUNTS_PER_INCH of lift
        double COUNTS_PER_MOTOR_REV = cpr;     //eg: Gobilda 13.7:1 Motor
        double DRIVE_GEAR_REDUCTION = gearRedox;     //No gear reduction on lift
        double WHEEL_DIAMETER_INCHES = wheelDiameter;    //Need to check this
        double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
    }

}
