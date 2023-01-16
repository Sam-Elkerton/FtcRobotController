package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
    public class MecanumTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        //declares motors
        DcMotor motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        DcMotor motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
        DcMotor motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
        DcMotor motorBackRight = hardwareMap.dcMotor.get("motorBackRight");
        DcMotor motorLift1 = hardwareMap.dcMotor.get("motorLift");
        Servo clawLeft = hardwareMap.servo.get("servoL");
        Servo clawRight = hardwareMap.servo.get("servoR");

        waitForStart();
        //reverses the left side motors
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);
        clawRight.setDirection(Servo.Direction.REVERSE);

        motorLift1.setMode(DcMotor.RunMode.RUN_TO_POSITION);




        //sets the motor powers to 0 on on start
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorFrontRight.setPower(0);
        motorBackRight.setPower(0);
        motorLift1.setPower(0);

        double speed = 0.75;
        boolean clawOpen = false;


        while (opModeIsActive()) {
            //stops the robot if button is turned off
            if (isStopRequested() == true) {
                break;
            } else {
                //sets the input values
                double y = gamepad1.left_stick_y;
                double x = -gamepad1.left_stick_x;
                double rx = gamepad1.right_stick_x;

                motorFrontLeft.setPower((speed) * (y + x - rx));
                motorBackLeft.setPower((speed) * (y - x - rx));
                motorFrontRight.setPower((speed) * (y - x + rx));
                motorBackRight.setPower((speed) * (y + x + rx));

                if(gamepad1.b == true) {
                    speed = 0.25;
                }else{
                    speed = 0.75;
                }
/*
                if (gamepad1.right_bumper == true) {
                    motorLift1.setPower(liftSpeed);
                }
                if (gamepad1.left_bumper == true) {
                    motorLift1.setPower(reverseLiftSpeed);
                }
                motorLift1.setPower(0);
*/
                if(gamepad1.dpad_up == true){
                    motorLift1.setTargetPosition(384*38);
                    motorLift1.setPower(1);
                }

                if(gamepad1.dpad_left == true){
                    motorLift1.setTargetPosition(384*28);
                    motorLift1.setPower(1);
                }

                if(gamepad1.dpad_right == true){
                    motorLift1.setTargetPosition(384*18);
                    motorLift1.setPower(1);
                }

                if(gamepad1.dpad_down == true){
                    motorLift1.setTargetPosition(384*8);
                    motorLift1.setPower(1);
                }


                if (gamepad1.a == true && clawOpen == true) {
                    clawLeft.setPosition(1);
                    clawRight.setPosition(1);
                    clawOpen = false;
                    sleep(100);
                }
                if (gamepad1.a == true && clawOpen == false) {
                    clawLeft.setPosition(0);
                    clawRight.setPosition(0);
                    clawOpen = true;
                    sleep(100);
                    }
                }
            }
        }
    }