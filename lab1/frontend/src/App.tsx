import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import ListDoctor from './components/doctor/ListDoctor';
import CreateDoctor from './components/doctor/CreateDoctor';
import EditDoctor from './components/doctor/EditDoctor';
import ListPatient from './components/patient/ListPatient';
import CreatePatient from './components/patient/CreatePatient';
import EditPatient from './components/patient/EditPatient';
import ListDiagnosis from './components/diagnosis/DiagnosisListComponent';
import CreateDiagnosis from './components/diagnosis/DiagnosisCreateComponent';
import EditDiagnosis from './components/diagnosis/DiagnosisEditComponent';

const App: React.FC = () => {
  return (
    <Router>
      <Switch>
        <Route path="/doctors" exact component={ListDoctor} />
        <Route path="/doctors/create" component={CreateDoctor} />
        <Route path="/doctors/edit/:id" component={EditDoctor} />
        <Route path="/patients" exact component={ListPatient} />
        <Route path="/patients/create" component={CreatePatient} />
        <Route path="/patients/edit/:id" component={EditPatient} />
        <Route path="/diagnoses" exact component={ListDiagnosis} />
        <Route path="/diagnoses/create" component={CreateDiagnosis} />
        <Route path="/diagnoses/edit/:id" component={EditDiagnosis} />
      </Switch>
    </Router>
  );
};

export default App;
