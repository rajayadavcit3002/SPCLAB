import org.cloudbus.cloudsim.*;
import org.cloudbus.cloudsim.core.cloudsim;
import java.util.*;

public class customschedulingsimulation{

public static void main(String[] args){

//Initialize the cloudsim simulation enivornment
Int numUsers=1;
Calendar calendar=calendar.getInstance();
Cloudsim.init(numUsers,calendar,false);

//create a datacenter
Datacenter=createDatacenter("Datacenter_0");

//Create a broker
DatacenterBroker broker=createBroker();

//Set the custom VM allocation policy
VmAllocationPolicy policy=new CustomSchedulingpolicy(datacenter.getHostList());
broker.setDatacenter(datacenter);
broker.setVmAllocationPolicy(policy);

// Create and submit cloudlets to the broker
int numVMs=5;
int num cloudlets=10;
createVMsAndCloudlets(broker,numVMs,numCloudlets);

// start the simulation
cloudsim.stopSimulation();

//Display the results
printResults(finishedcloudlets);
}

private static Datacenter createDatacenter(String name){

List<Host>hostList= newArrayList< > ();

// Create hosts with required characteristics
//Define host properties like MIPS,RAM,storage,bandwidth,etc.
//Use Host and other related classes in cloudsim

for (int i=0;i<3;i++){

int mips=1000; //Example MIPS
value int ram=2048; // Example RAM
value
long storage=1000000; //Example storage value
int bw=10000; //Example bandwidth value

hostList.add(
new Host(
i,
new RamProvisionSimple(ram),
new BwProvisionersimple(bw),
storage,
new ArrayList<pe>(),
new Vmschedulespaceshared(new ArrayList<pe>())
)
);

}

// Create DatacenterCharacteristics and return a Datacenter object

String arch="x86";
String os="Linux";
String vmm="xen";
double time_zone=10.0;
double cost=3.0;
double costPerMem=0.05;
double costPerStorage=0.001;
double costPerBw=0.0;

DatacenterCharacteristics characteristics =
new DatacenterCharacteristics(
arch,
os,
vmm,
hostList,
time_zone,
cost,
costPerStorgae,
costPerBw
);

Datacenter datacenter=null;

try{

datacenter=
new Datacenter(
name,
characterstics,
new VmAllocationPolicySimple(hostList),
newArrayList<storage>(),
0
);

}catch(exception e){

e.printstackTrace();

}

return datacenter;

}

private static void createVMsAndCloudlets(
DatacenterBroker broker,
int numVMs,
int numCloudlets
){

List<Vm>vmList=new ArrayList< >();
List<Cloudlet>cloudletList=new ArrayList< >();

//create VMs with required characteristics
// Define VM properties like MIPS,RAM,storage,bandwidth,etc.
//Use Vm and other related classes in Cloudsim

for(uint i=0;i<numVms;i++){

int mips=1000;
value int ram=512;
value
long size=100000;
value int bw=1000;
value int pesNumber=1;

Vm vm=new Vm(
i,
broker.getId(_),
mips,
pesNumber,
ram,
bw,
size,
"xen",
cloudletscheduleTimeShared()
);

vmList.add(vm);

}

//Create cloudlets with required characteristics
// Define cloudlet length, utilization model,etc.
//Use cloudlet and other related classes in cloudSim

for(int i=0;i<numCloudlets;i++){

long length=100000;
int pesNumber=1;
long fileSize=300;
long outputSize=300;

UtilizationModel utilizationModel=new UtilizationModelFull();

Cloudlet cloudlet=
new cloudlet(
i,
length,
pesNumber,
fileSize,
outputSize,
utilizationModel,
utilizationModel
);

cloudlet.setUserId(broker.getId());

cloudletList.add(cloudlet);

broker.bindcloudletTovm(
cloudlet.getId(),
vmList.get(i % numVMs).getId()
);

}

broker.submitVmList(vmList);
broker.submitCloudletList(cloudletList);

}

private static void printResults(list<cloudlet> cloudlets){

// process and print the simulation results
// Display performance metrics like makespan,resource utilization,response time.

for(Cloudlet cloudlet:cloudlets){

System.out.println(
"CloudletID:"
+ cloudlet.getCloudletId()
+ ",VM ID:"
+ cloud.getVm
+ ", Status:"
+ cloudlet.getStatus()
+ ", StartTime:"
+ cloudlet.getExecStartTime()
+ ", Finish Time:"
+ cloudlet.getFinishTime()
);

}

}

}
