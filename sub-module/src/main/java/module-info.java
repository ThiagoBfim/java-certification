
module sub.module {
    requires transitive task;
        exports org.bomfim.animal;
        opens org.bomfim.animal;
    provides org.bomfim.service.AnimalService with org.bomfim.serviceanimal.AnimalServiceImpl;

}
