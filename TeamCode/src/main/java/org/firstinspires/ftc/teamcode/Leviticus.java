package org.firstinspires.ftc.teamcode;

import android.icu.text.UFormat;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class Leviticus extends LinearOpMode{
    //declare variables
    DcMotor motorFrontLeft = null;
    DcMotor motorBackLeft = null;
    DcMotor motorFrontRight = null;
    DcMotor motorBackRight = null;
    DcMotor motorLift1 = null;
    Servo clawLeft = null;
    Servo clawRight = null;
    //this is dependent on motor type and gear ration( look on website in order to find this)
    double ticks_per_rev = 384;
    //diameter of the wheel --> or in the case of the slides the diameter of the winding wheel
    double wheel_diameter = 4; //will need to actually measure this
    //calculation of the amount of ticks required in order for the motor to move one inch
    double ticks_per_inch = (ticks_per_rev*wheel_diameter*3.1415926535);
    //these are adjustable speeds of the motors --> allows for changes to the speed
    double drive_speed = 1;
    double turn_speed = 1;
    //this is for keeping track of how much time has passed between movements
    private ElapsedTime     runtime = new ElapsedTime();

    @Override
    public void runOpMode(){

        //declaration of all of the hardware
       motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
       motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
       motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
       motorBackRight = hardwareMap.dcMotor.get("motorBackRight");
       motorLift1 = hardwareMap.dcMotor.get("motorLift");
       clawLeft = hardwareMap.servo.get("servoL");
       clawRight = hardwareMap.servo.get("servoR");

       //stops and resets the encoders on start
       motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       motorLift1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

       //starts to run the encoders for each of the motors
       motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
       motorBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
       motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
       motorBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
       motorLift1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

       //waits for init to be over and for the beginning of autonomous
        waitForStart();
        telemetry.addData("Beginning Autonamous", "Running Autonamous");
        telemetry.update();

        //this is all of the functions which actually moves the robot
        drive(drive_speed, 10, 100);


    }

    public void drive(double speed, double distance, int sleep){
        //stuff
        int newFLTarget;
        int newBLTarget;
        int newFRTarget;
        int newBRTarget;


        if( opModeIsActive()){
            newFLTarget = motorFrontLeft.getCurrentPosition() + (int)(distance * ticks_per_inch);
            newBLTarget = motorBackLeft.getCurrentPosition() + (int)(distance * ticks_per_inch);
            newFRTarget = motorFrontRight.getCurrentPosition() + (int)(distance * ticks_per_inch);
            newBRTarget = motorBackRight.getCurrentPosition() + (int)(distance + ticks_per_inch);

            motorFrontLeft.setTargetPosition(newFLTarget);
            motorBackLeft.setTargetPosition(newBLTarget);
            motorFrontRight.setTargetPosition(newFRTarget);
            motorBackRight.setTargetPosition(newBRTarget);

            motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            runtime.reset();
            motorFrontLeft.setPower(speed);
            motorBackLeft.setPower(speed);
            motorFrontRight.setPower(speed);
            motorBackRight.setPower(speed);

            while(opModeIsActive() && (motorFrontLeft.isBusy() && motorFrontRight.isBusy() && motorBackRight.isBusy() && motorBackLeft.isBusy())) {
                telemetry.addData("Running to",  " %7d :%7d", newFLTarget,  newBLTarget, newFRTarget, newBRTarget);
                telemetry.addData("Currently at",  " at %7d :%7d", motorFrontLeft.getCurrentPosition(), motorBackLeft.getCurrentPosition(), motorFrontRight.getCurrentPosition(), motorBackRight.getCurrentPosition());
                telemetry.update();
            }

            motorFrontLeft.setPower(0);
            motorBackLeft.setPower(0);
            motorFrontRight.setPower(0);
            motorBackRight.setPower(0);

            motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motorBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motorBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motorLift1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            sleep(sleep);

        }

    }


    public void turn(double speed, double angle, int sleep){

        int newFLTarget;
        int newBLTarget;
        int newFRTarget;
        int newBRTarget;


        if( opModeIsActive()){
        newFLTarget = motorFrontLeft.getCurrentPosition() + (int)(/* this  needs to be the equation for calculating how many ticks for angle */);
        newBLTarget = motorBackLeft.getCurrentPosition() + (int)(/* this  needs to be the equation for calculating how many ticks for angle */);
        newFRTarget = motorFrontRight.getCurrentPosition() + (int)(/* this  needs to be the equation for calculating how many ticks for angle --> Inversed*/);
        newBRTarget = motorBackRight.getCurrentPosition() + (int)(/* this  needs to be the equation for calculating how many ticks for angle --> Inversed*/);

        motorFrontLeft.setTargetPosition(newFLTarget);
        motorBackLeft.setTargetPosition(newBLTarget);
        motorFrontRight.setTargetPosition(newFRTarget);
        motorBackRight.setTargetPosition(newBRTarget);

        motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        runtime.reset();
        motorFrontLeft.setPower(speed);
        motorBackLeft.setPower(speed);
        motorFrontRight.setPower(speed);
        motorBackRight.setPower(speed);

        while(opModeIsActive() && (motorFrontLeft.isBusy() && motorFrontRight.isBusy() && motorBackRight.isBusy() && motorBackLeft.isBusy())) {
            telemetry.addData("Running to",  " %7d :%7d", newFLTarget,  newBLTarget, newFRTarget, newBRTarget);
            telemetry.addData("Currently at",  " at %7d :%7d", motorFrontLeft.getCurrentPosition(), motorBackLeft.getCurrentPosition(), motorFrontRight.getCurrentPosition(), motorBackRight.getCurrentPosition());
            telemetry.update();
        }

        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorFrontRight.setPower(0);
        motorBackRight.setPower(0);

        motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLift1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        sleep(sleep);

    }

    }
}
