package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
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
            Servo claw1 = hardwareMap.servo.get("claw");

            waitForStart();
            //reverses the left side motors
            motorBackLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            motorFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

            //sets the motor powers to 0 on on start
            motorFrontLeft.setPower(0);
            motorBackLeft.setPower(0);
            motorFrontRight.setPower(0);
            motorBackRight.setPower(0);
            motorLift1.setPower(0);


            while (opModeIsActive()) {
                //stops the robot if button is turned off
                if(isStopRequested() == true){
                    break;
                }else{
                    //sets the input values
                    double speed = 0.75;
                    double liftSpeed = 0.75;
                    double y = gamepad1.left_stick_y; // Remember, this is reversed!
                    double x = -gamepad1.left_stick_x;
                    double rx = gamepad1.right_stick_x;

                    //sets the power of the motors to the inputs of the controller (only 75% power)
                    motorFrontLeft.setPower((speed) * (y + x + rx));
                    motorBackLeft.setPower((speed) * (y - x + rx));
                    motorFrontRight.setPower((speed) * (y - x - rx));
                    motorBackRight.setPower((speed) * (y + x - rx));

                    while(gamepad1.right_bumper == true){
                        motorLift1.setPower(liftSpeed);
                    }

                    if(gamepad1.a == true){
                        claw1.setPosition(1);
                    }else{
                        claw1.setPosition(0);
                    }
                }
            }
        }
    }