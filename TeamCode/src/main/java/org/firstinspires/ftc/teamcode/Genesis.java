package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp
    public class Genesis extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        //declares motors + servos
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

        //encoder stuff
        motorLift1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLift1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);



        //sets the motor powers to 0 on on start
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorFrontRight.setPower(0);
        motorBackRight.setPower(0);
        motorLift1.setPower(0);

        //declares variables
        double speed = 0.75;
        boolean clawOpen = false;


        while (opModeIsActive()) {
            //stops the robot if button is turned off
            if (isStopRequested() == true) {
                break;
            } else {
                //sets the input values for movement
                double y = gamepad1.left_stick_y;
                double x = -gamepad1.left_stick_x;
                double rx = gamepad1.right_stick_x;

                motorFrontLeft.setPower((speed) * (y + x - rx));
                motorBackLeft.setPower((speed) * (y - x - rx));
                motorFrontRight.setPower((speed) * (y - x + rx));
                motorBackRight.setPower((speed) * (y + x + rx));

                //decreases the speed of the robot if B is pressed --> allows for more precise movement when placing or picking up cones
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
            //using encoders the motors are able to travel a specific distance and will hold its position
                //moves to highest pole (38'')
                if(gamepad1.dpad_up == true){
                    motorLift1.setTargetPosition(384*15);
                    motorLift1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    motorLift1.setPower(0.75);
                }

                //moves to medium pole (28'')
                if(gamepad1.dpad_left == true){
                    motorLift1.setTargetPosition(384*10);
                    motorLift1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    motorLift1.setPower(0.75);
                }

                //moves to low pole (18'')
                if(gamepad1.dpad_right == true){
                    motorLift1.setTargetPosition(384*5);
                    motorLift1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    motorLift1.setPower(0.75);
                }

                //lifts up cone just enough to place on button thingy (8'')
                if(gamepad1.dpad_down == true){
                    motorLift1.setTargetPosition(0);
                    motorLift1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    motorLift1.setPower(0.25);
                    motorLift1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                }

            //opens claw
                if (gamepad1.a == true && clawOpen == true) {
                    clawLeft.setPosition(1);
                    clawRight.setPosition(1);
                    clawOpen = false;
                    sleep(200);
                }
                if (gamepad1.a == true && clawOpen == false) {
                    clawLeft.setPosition(0);
                    clawRight.setPosition(0);
                    clawOpen = true;
                    sleep(200);
                    }
                }
            }
        }
    }