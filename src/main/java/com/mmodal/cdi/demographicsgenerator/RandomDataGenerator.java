//package com.mmodal.cdi.demographicsgenerator;
//
//import java.nio.file.Path;
//import java.util.*;
//
///**
// * Created by brian.ellenberger on 3/6/2017.
// */
//public class RandomDataGenerator {
//    private Map<String, Random> randomMap;
//    private ScenarioLoadData loadData;
//
//    public enum RANDOM_TYPE {
//        Name,
//        gender,
//        race,
//        zipcodes
//    }
//
//    public RandomDataGenerator(ScenarioLoadData loadData) {
//        initRandoms(settings);
//        this.loadData = loadData;
//        this.settings = settings;
//    }
//
//    private void initRandoms() {
//        randomMap = new HashMap<>();
//
//        Arrays.stream(RANDOM_TYPE.values()).forEach(
//                x -> {
//                    randomMap.put(x.toString(), new Random(settings.getRandomSeed()));
//                }
//        );
//    }
//
//    public int nextInt(RANDOM_TYPE type, int bound) {
//        return randomMap.get(type.toString()).nextInt(bound);
//    }
//
//    public boolean nextBoolean(RANDOM_TYPE type) {
//        return randomMap.get(type.toString()).nextBoolean();
//    }
//
//    public double nextDouble(RANDOM_TYPE type) {
//        return randomMap.get(type.toString()).nextDouble();
//    }
//
//    public double nextGaussian(RANDOM_TYPE type) {
//        return randomMap.get(type.toString()).nextGaussian();
//    }
//
//    public String getPatientID() {
//        int randId = (int)Math.floor(this.nextDouble(RANDOM_TYPE.PatientID) * 1E8);
//        return Integer.toString(randId) + "A"; //Adding a letter just so that we don't assume numeric
//    }
//
//    public String getEncounterID() {
//        int randId = (int)Math.floor(this.nextDouble(RANDOM_TYPE.EncounterID) * 1E8);
//        return Integer.toString(randId) + "B"; //Adding a letter just so that we don't assume numeric
//    }
//
//    public String getDocumentID() {
//        int randId = (int)Math.floor(this.nextDouble(RANDOM_TYPE.DocumentID) * 1E8);
//        return Integer.toString(randId) + "C"; //Adding a letter just so that we don't assume numeric
//    }
//
//    public String getFemaleFirstName() {
//        return loadData.getFemaleFirstName().get(this.nextInt(RANDOM_TYPE.Name, loadData.getFemaleFirstName().size()));
//    }
//
//    public String getMaleFirstName() {
//        return loadData.getMaleFirstName().get(this.nextInt(RANDOM_TYPE.Name, loadData.getMaleFirstName().size()));
//    }
//
//    public String getLastName() {
//        return loadData.getLastNames().get(this.nextInt(RANDOM_TYPE.Name, loadData.getLastNames().size()));
//    }
//
//    public FakeInsurance getPrimaryInsurance() {
//        FakeInsurance insurance =  loadData.getInsuranceList().get(this.nextInt(RANDOM_TYPE.PrimaryInsurance, loadData.getInsuranceList().size()));
//        insurance.setPrimary(true);
//
//        return insurance;
//    }
//
//    public FakeInsurance getSecondaryInsurance() {
//        FakeInsurance insurance =  loadData.getSecondaryInsuranceList().get(this.nextInt(RANDOM_TYPE.SecondaryInsurance, loadData.getSecondaryInsuranceList().size()));
//        insurance.setPrimary(false);
//
//        return insurance;
//    }
//
//    public String getHospitalID() {
//        return Integer.toString((int)Math.floor(this.nextDouble(RANDOM_TYPE.Hospitals) * 1E8)) + "B";
//    }
//
//    public String getHospitalName() {
//        return loadData.getHospitalList().get(this.nextInt(RANDOM_TYPE.Hospitals, loadData.getHospitalList().size()));
//    }
//
//    public FakeHospital getHospital(List<FakeHospital> hospitals) {
//        return hospitals.get(this.nextInt(RANDOM_TYPE.Hospitals, hospitals.size()));
//    }
//
//    public FakeDoctor getDoctor(List<FakeDoctor> doctors) {
//        return doctors.get(this.nextInt(RANDOM_TYPE.Doctor, doctors.size()));
//    }
//
//    public String getUnit() {
//        return loadData.getHospitalUnitList().get(this.nextInt(RANDOM_TYPE.PatientDemos, loadData.getHospitalUnitList().size()));
//    }
//
//    public int getRoom() {
//        return this.nextInt(RANDOM_TYPE.PatientDemos, 199) + 100;
//    }
//
//    public int getBed() {
//        return this.nextInt(RANDOM_TYPE.PatientDemos, 2) + 1;
//    }
//
//    public CodeSets getCodeSet() {
//        return loadData.getCodeSets().get(this.nextInt(RANDOM_TYPE.CodeSet, loadData.getCodeSets().size()));
//    }
//
//    public Path getNarrative() {
//        return loadData.getNarratives().get(this.nextInt(RANDOM_TYPE.Narrative, loadData.getNarratives().size()));
//    }
//
//    public String getPatientClass() {
//        return weightedOption(RANDOM_TYPE.PatientDemos, settings.getPatientClassOptions(), 0, .75);
//    }
//
//    public String getPatientType() {
//        return weightedOption(RANDOM_TYPE.PatientDemos, settings.getPatientTypeOptions(), 0, .75);
//    }
//
//    public String getPayerType() {
//        return weightedOption(RANDOM_TYPE.PatientDemos, settings.getPayerTypeOptions(), 0, .75);
//    }
//
//    private String weightedOption(RANDOM_TYPE type, String options[], int primary, double weight) {
//
//        if (this.nextDouble(type) <= weight) {
//            return options[primary];
//        }
//
//        return options[this.nextInt(type, options.length)];
//    }
//
//    public Date generateRandomDate(Date startDate, int maxDays, double dateRangeWeight, boolean includeTime, boolean past) {
//
//        int numberDaysBack = Integer.MAX_VALUE;
//
//        while (numberDaysBack >= maxDays)
//            numberDaysBack = (int)Math.abs(this.nextGaussian(RANDOM_TYPE.GeneralDate)*dateRangeWeight);
//
//        //If this is a date in the past, it is a negative.  Otherwise positive
//        if (past)
//            numberDaysBack *= -1;
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(startDate);
//        calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
//
//        calendar.add(Calendar.DAY_OF_MONTH, numberDaysBack);
//
//        if (includeTime) {
//            calendar.set(Calendar.HOUR, this.nextInt(RANDOM_TYPE.GeneralDate, 23));
//            calendar.set(Calendar.MINUTE, this.nextInt(RANDOM_TYPE.GeneralDate, 59));
//            calendar.set(Calendar.SECOND, 0);
//        } else {
//            calendar.set(Calendar.HOUR, 0);
//            calendar.set(Calendar.MINUTE, 0);
//            calendar.set(Calendar.SECOND, 0);
//        }
//
//        return calendar.getTime();
//    }
//
//    public Date generateRandomDOB(int ageStandardDev, int ageOffset, int minAge, int maxAge) {
//
//        int yearsBack = Integer.MAX_VALUE;
//
//        while (yearsBack < minAge || yearsBack > maxAge)
//            yearsBack = (int)Math.floor((double) (this.nextGaussian(RANDOM_TYPE.DOBDate)*ageStandardDev) + ageOffset);
//
//        Calendar calendar = Calendar.getInstance();
//
//        calendar.add(Calendar.YEAR, (yearsBack * -1));
//        calendar.set(Calendar.MONTH, this.nextInt(RANDOM_TYPE.DOBDate, 11));
//        calendar.set(Calendar.DAY_OF_MONTH, this.nextInt(RANDOM_TYPE.DOBDate, 28));
//        calendar.set(Calendar.HOUR, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//
//
//        return calendar.getTime();
//    }
//}
