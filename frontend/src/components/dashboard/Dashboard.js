import React from "react";
import Container from "react-bootstrap/Container";
import CentralHeader from "./CentralHeader";
import RecruitmentHelperInfo from "./RecruitmentHelperInfo";
import AllObjectsDataOverview from "./AllObjectsDataOverview";

class Dashboard extends React.Component {
    render() {
        return (
            <Container className={'dashboard-container'} fluid>
                <CentralHeader
                    versionNumber={"v0.1"}
                    pageTitle={"System rezerwacji sal komputerowych"}
                    subTitle={"na potrzeby przeprowadzania egzaminów na studia II stopnia"}/>
                <hr/>
                <RecruitmentHelperInfo currentRecruitment="LATO 2020"/>
                <AllObjectsDataOverview objects={this.props.objects}/>
            </Container>
        );
    }
}

export default Dashboard;