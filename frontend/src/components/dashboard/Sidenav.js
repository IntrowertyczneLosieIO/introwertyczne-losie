import React from "react";
import Nav from "react-bootstrap/Nav";
import Button from "react-bootstrap/Button";
import AddNewUser from "../forms/AddNewUser";

class Sidenav extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            showAddNew: false
        }
    }

    setShowAddNew = (show) => {
        this.setState({
            showAddNew: show
        })
    }

    handleShow = () => this.setShowAddNew(true);
    handleHide = () => this.setShowAddNew(false);

    render() {
        let nameUrlMapping = {
            "Majors": "/majors",
            "Rooms": "/rooms",
            "Exams": "/exams"
        };

        let objectsList = this.props.objects.map((object) => {
            return <Nav.Link href={nameUrlMapping[object.objectsSetName]} key={object.key}><Button
                variant={"outline-dark"} block size={"sm"}>{object.displayName}</Button></Nav.Link>
        })

        return (
            <>
            <Nav fill activeKey={"#"} className={"flex-column mt-3"}>
                <Nav.Link href={"/"}><Button variant={"outline-dark"}
                                             block size={"sm"}>Strona główna</Button></Nav.Link>
                <Nav.Link href={"#"}><Button variant={"outline-dark"}
                                             block size={"sm"}>Rekrutacje</Button></Nav.Link>
                {objectsList}
                <Nav.Link href={"#"} className={"mt-4"}><Button variant={"outline-dark"} block size={"sm"}>Bieżąca rekrutacja</Button></Nav.Link>
                <Nav.Link href={"#"}><Button variant={"success"} block size={"sm"} onClick={this.handleShow}>Dodaj nowego użytkownika</Button></Nav.Link>
                {/*<Nav.Link href={"#"}><Button variant={"outline-dark"} block>Wydziały</Button></Nav.Link>*/}
            </Nav>

                <AddNewUser handleShow={this.handleShow} handleHide={this.handleHide} show={this.state.showAddNew} />
            </>
        );
    }
}

export default Sidenav;