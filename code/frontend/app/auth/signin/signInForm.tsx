"use client";
import { useState } from "react";
import { useRouter } from "next/navigation";
import { signIn } from "next-auth/react";
import "react-toastify/dist/ReactToastify.css";
import { ToastContainer, toast } from "react-toastify";
import Link from "next/link";
import { apiAuthSignUp } from "@/utils/api";

export default function SignUpForm() {
    const router = useRouter();
    const [formData, setFormData] = useState({
        username: "",
        email: "",
        password: "",
        confirmPassword: "",
        fullName: "",
        phoneNumber: "",
        age: 0,
        userType: "chatter" as "admin" | "chatter"
    });

    const { username, email, password, confirmPassword, fullName, phoneNumber, age, userType } = formData;

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        if (password !== confirmPassword) {
            toast.error("Passwords do not match");
            return;
        }

        try {
            const result = await apiAuthSignUp({
                username,
                email,
                password,
                fullName,
                phoneNumber,
                age: Number(age),
                userType,
                role: userType === "admin" ? "GENERAL_ADMIN" : "REGULAR_USER"
            });

            if (result.error) {
                toast.error(result.error);
                return;
            }

            // Auto sign in after successful registration
            const signInResult = await signIn("credentials", {
                username,
                password,
                redirect: false,
            });

            if (signInResult?.ok) {
                router.push("/dashboard");
            } else {
                toast.success("Registration successful! Please sign in.");
                router.push("/auth/signin");
            }
        } catch (error) {
            toast.error("Registration failed");
        }
    };

    return (
        <div className="flex justify-center items-center h-screen">
            <form
                onSubmit={handleSubmit}
                className="bg-gray-200 shadow-md rounded px-8 pt-6 pb-8 mb-4 w-full sm:w-1/2 md:w-1/3 lg:w-1/4 flex flex-col"
            >
                <h1 className="text-3xl font-bold mb-4">Sign Up</h1>

                <div className="mb-4">
                    <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="userType">
                        Account Type
                    </label>
                    <select
                        className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        name="userType"
                        value={userType}
                        onChange={handleChange}
                    >
                        <option value="chatter">Chatter</option>
                        <option value="admin">Admin</option>
                    </select>
                </div>

                <div className="mb-4">
                    <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="username">
                        Username
                    </label>
                    <input
                        className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        type="text"
                        name="username"
                        placeholder="Username"
                        value={username}
                        onChange={handleChange}
                        required
                    />

                    <label className="block text-gray-700 text-sm font-bold mb-2 mt-3" htmlFor="email">
                        Email
                    </label>
                    <input
                        className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        type="email"
                        name="email"
                        placeholder="Email"
                        value={email}
                        onChange={handleChange}
                    />

                    <label className="block text-gray-700 text-sm font-bold mb-2 mt-3" htmlFor="fullName">
                        Full Name
                    </label>
                    <input
                        className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        type="text"
                        name="fullName"
                        placeholder="Full Name"
                        value={fullName}
                        onChange={handleChange}
                    />

                    <label className="block text-gray-700 text-sm font-bold mb-2 mt-3" htmlFor="phoneNumber">
                        Phone Number
                    </label>
                    <input
                        className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        type="text"
                        name="phoneNumber"
                        placeholder="Phone Number"
                        value={phoneNumber}
                        onChange={handleChange}
                    />

                    <label className="block text-gray-700 text-sm font-bold mb-2 mt-3" htmlFor="age">
                        Age
                    </label>
                    <input
                        className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        type="number"
                        name="age"
                        placeholder="Age"
                        value={age}
                        onChange={handleChange}
                    />
                </div>

                <div className="mb-4">
                    <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="password">
                        Password
                    </label>
                    <input
                        className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        type="password"
                        name="password"
                        placeholder="******************"
                        value={password}
                        onChange={handleChange}
                        required
                    />

                    <label className="block text-gray-700 text-sm font-bold mb-2 mt-3" htmlFor="confirmPassword">
                        Confirm Password
                    </label>
                    <input
                        className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        type="password"
                        name="confirmPassword"
                        placeholder="******************"
                        value={confirmPassword}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="flex items-center justify-between">
                    <button
                        className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                        type="submit"
                    >
                        Sign Up
                    </button>
                    <Link
                        className="inline-block align-baseline font-bold text-sm text-blue-500 hover:text-blue-800"
                        href="/auth/signin"
                    >
                        Already have an account?
                    </Link>
                </div>
            </form>
            <ToastContainer autoClose={3000} hideProgressBar />
        </div>
    );
}
