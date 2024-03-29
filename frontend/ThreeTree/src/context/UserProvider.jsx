import {createContext, useCallback, useContext, useEffect, useState,} from "react";
import PropTypes from "prop-types";


const UserContext = createContext({});

const getToken = () => window.localStorage.getItem("token");
const setToken = (token) => window.localStorage.setItem("token", token);


const UserProvider = ({children}) => {

    UserProvider.propTypes = {
        children: PropTypes.any.isRequired
    };

    const [userName, setUserName] = useState();
    const [loading, setLoading] = useState(true);

    const fetchUserName = useCallback((token) => {
        fetch('/api/customers/me', {
            headers: {
                'authorization': `bearer ${token}`
            },
        })
            .then((r) => r.json())
            .then((userName) => {
                setUserName(userName);
            })
            .finally(() => {
                setLoading(false);
            });
    }, []);

    useEffect(() => {
        const token = getToken();

        if (!token) {
            setLoading(false);
            return;
        }
        fetchUserName(token);
    }, []);

    const signup = async (creds) => {
        try {
            const response = await fetch('/api/v1/auth/register', {
                method: 'POST', headers: {
                    'Content-Type': 'application/json'
                }, body: JSON.stringify(creds)
            });

            if (!response.ok) {
                throw new Error("ERROR: Failed to send request to server.");
            } else {
                const responseData = await response.json();
                const token = responseData.token;
                if (token) {
                    setToken(token);
                    await fetchUserName(token);
                    window.location.href = "/Products";
                    console.log(getToken());
                }
            }
        } catch (error) {
            console.error('Failed to send request:', error);
        }
    };

    const signin = (creds, token) => {
        fetch("/api/v1/auth/authenticate", {
            method: "POST", headers: {
                "authorization": `bearer ${token}`, "Content-Type": "application/json",
            }, body: JSON.stringify(creds),
        })
            .then((res) => res.json())
            .then((res) => {
                console.log(res.token);
                const jwt = res.token;
                if (jwt) {
                    setToken(jwt);
                    fetchUserName(jwt);
                    console.log(getToken());
                }
            });
    };

    const signout = () => {
        setUserName(null);
        setToken("");
    }

    return (<UserContext.Provider value={{
        user: fetchUserName, userName, signin, signup, signout, getToken, setToken
    }}
    >
        {!loading && children}
    </UserContext.Provider>);
};

export const useUser = () => useContext(UserContext);

export default UserProvider;