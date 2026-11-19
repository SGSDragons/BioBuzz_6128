package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="DO NOT USE!!!!! Competition Autonomous 2 ☻")
public class automodeComp extends OpMode {
    private Drive driver;
    private Operate operator;
    private ElapsedTime runtime;
    private double step;
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
        if (step==0){
            operator.shoot(Operate.Mode.POLLEN);
            if (operator.ReadyToShoot()){
                step++;
                runtime.reset();

            }
        } else if (step==1){
            operator.ballDrive(1);
            if (runtime.seconds()>=1){
                step++;
                runtime.reset();
                operator.ballDrive(0);
                operator.shoot(Operate.Mode.OFF);
            }
        } else if (step==2) {
            driver.drive(1, 0, 0);
            if (runtime.seconds()>=1) {
                step++;
                runtime.reset();
                driver.drive(0, 0, 0);
            }
        }
    }
}
