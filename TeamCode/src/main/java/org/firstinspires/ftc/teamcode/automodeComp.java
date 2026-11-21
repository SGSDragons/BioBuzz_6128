package org.firstinspires.ftc.teamcode;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
@Config
@Autonomous(name="DO NOT USE!!!!! Competition Autonomous 2 ☻")
public class automodeComp extends OpMode {
    private Drive driver;
    private Operate operator;
    private ElapsedTime runtime;
    private double step;

    public static double forwardTime = 1.075;
    public static double shootingTime = 2;
    public static double strafeTime = 2.2;


    @Override
    public void init(){
        driver = new Drive(null, hardwareMap);
        operator = new Operate(null, hardwareMap);
        runtime = new ElapsedTime();
        step = 0;
        runtime.reset();
    }

    @Override
    public void loop() {
        TelemetryPacket p = new TelemetryPacket();
        p.put("step #", step);
        FtcDashboard.getInstance().sendTelemetryPacket(p);
        /* shooting */
        if (step==0){
            operator.shoot(Operate.Mode.POLLEN);
            if (operator.readyToShoot()){
                step++;
                runtime.reset();

            }
        } else if (step==1){
            operator.ballDrive(1);
            if (runtime.seconds()>= shootingTime){
                step++;
                runtime.reset();
                operator.ballDrive(0);
                operator.shoot(Operate.Mode.OFF);
            }
            /* driving */
        }
        else if (step==2) {
            driver.drive(-1, 0, 0);
            if (runtime.seconds()>= forwardTime) {
                step++;
                runtime.reset();
                driver.drive(0, 0, 0);
            }
        } else if (step==3) {
            driver.drive(0, -1, 0);
            if (runtime.seconds()>= strafeTime) {
                step++;
                runtime.reset();
                driver.drive(0, 0, 0);
            }

        }
    }
}
